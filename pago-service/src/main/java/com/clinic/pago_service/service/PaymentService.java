package com.clinic.pago_service.service;

import com.clinic.pago_service.Domain.DTO.request.PaymentSpecificationDTO;
import com.clinic.pago_service.Domain.Factura;
import com.clinic.pago_service.Domain.payment.Payment;
import com.clinic.pago_service.event.Model.Consultation;
import com.clinic.pago_service.event.Model.Pacientes;
import com.clinic.pago_service.event.Model.PaymentModel;
import com.clinic.pago_service.event.Model.medical_services.MedicalPackage;
import com.clinic.pago_service.event.Model.medical_services.MedicalService;
import com.clinic.pago_service.event.Model.medical_services.MedicalServices;
import com.clinic.pago_service.event.PaymentEvent;
import com.clinic.pago_service.event.published.ConfirmedPayEvent;
import com.clinic.pago_service.repository.ServicioMedicoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PaymentService {

    private final com.clinic.pago_service.repository.PaymentRepository paymentRepository;
    private final FacturaService invoiceService;
    private final com.clinic.pago_service.Mapper.PaymentMapper paymentMapper;
    private final ApplicationEventPublisher eventPublisher;
    private final ExportService exportService;
    private final com.clinic.pago_service.repository.ConsultationRepository consultationRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ServicioMedicoRepository medicalServicesRepository;
    private final EmailSendService emailSendService;

    @Transactional
    public ResponseEntity<byte[]> createPayment(com.clinic.pago_service.Domain.DTO.request.CreatePaymentDTO paymentDTO) {
        com.clinic.pago_service.Domain.payment.Payment payment = new Payment();
        payment.setConsultationId(paymentDTO.consultationId());
        Consultation consultation = consultationRepository.getConsultation(payment.getConsultationId());
        payment.setAmount(consultation.getFinalPrice());
        payment.setFactura(invoiceService.createInvoice(payment));
        payment.setPaymentMethod(paymentDTO.paymentMethod());

        paymentRepository.save(payment);
        kafkaTemplate.send("confirmed-pay", new ConfirmedPayEvent(payment.getConsultationId()));

        for (MedicalServices medicalService : consultation.getMedicalServicesModel()) {
            if (medicalService.getClass() == MedicalService.class) {
                eventPublisher.publishEvent(new PaymentEvent(medicalService.getPrice(), payment.getPaymentDate(),
                        ((MedicalService) medicalService).getServiceType(), ((MedicalService) medicalService).getSpeciality().getName()));
            }else {
                MedicalPackage medicalPackage = medicalServicesRepository.getPackageById(medicalService.getId());
                for (MedicalService medicalService1 : medicalPackage.getMedicalServices()) {
                    eventPublisher.publishEvent(new PaymentEvent(medicalService1.getPrice(), payment.getPaymentDate(),
                            medicalService1.getServiceType(), medicalService1.getSpeciality().getName()));
                }
            }
        }


        Pacientes patient = consultation.getPatient();
        List<MedicalServices> medicalServices = consultation.getMedicalServicesModel();
        Factura invoice = payment.getFactura();

        Map<String, Object> receiptData = new HashMap<>();
        receiptData.put("receiptNumber", String.format("%010d", payment.getId()));
        receiptData.put("invoiceNumber", String.format("%010d", invoice.getId()));
        receiptData.put("paymentDate", payment.getPaymentDate().toLocalDate());
        receiptData.put("patientName", patient.getName());
        receiptData.put("patientAddress", patient.getAddress().toString());
        receiptData.put("patientProvince", "La Plata");
        receiptData.put("patientDni", patient.getDni());
        receiptData.put("patientPhone", patient.getPhone());
        receiptData.put("patientEmail", patient.getEmail());
        receiptData.put("finalPrice", consultation.getFinalPrice());
        receiptData.put("services", medicalServices);
        receiptData.put("paymentMethod", payment.getPaymentMethod().getDisplayName());

        kafkaTemplate.send("notification", emailSendService.createPaidNotificationEvent(payment, consultation, exportService.getReceiptPDF(receiptData)));
        return invoiceService.getInvoiceByPayment(payment.getId());
    }

    public Boolean cancelPayment(Long paymentId) {
        Payment payment = paymentRepository.findByIdAndCanceled(paymentId, false)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found with id: " + paymentId));
        Consultation consultation = consultationRepository.getConsultation(payment.getConsultationId());
        payment.setCanceled(true);

        for (MedicalServices medicalService : consultation.getMedicalServicesModel()) {
            if (medicalService.getClass() == MedicalService.class) {
                eventPublisher.publishEvent(new PaymentEvent(medicalService.getPrice().negate(), payment.getPaymentDate(),
                        ((MedicalService) medicalService).getServiceType(), ((MedicalService) medicalService).getSpeciality().getName()));
            }else {
                for (MedicalService medicalService1 : ((MedicalPackage) medicalService).getMedicalServices()) {
                    eventPublisher.publishEvent(new PaymentEvent(medicalService1.getPrice().negate(), payment.getPaymentDate(),
                            medicalService1.getServiceType(), medicalService1.getSpeciality().getName()));
                }
            }
        }
        return true;
    }

    public Page<com.clinic.pago_service.event.Model.PaymentModel> getAllPayments(Pageable pageable, PaymentSpecificationDTO specificationDTO) {

        Specification<com.clinic.pago_service.Domain.payment.Payment> spec =
                Specification.where(com.clinic.pago_service.specification.PaymentSpecification.paymentByCanceled(specificationDTO.getCanceled()))
                        .and(com.clinic.pago_service.specification.PaymentSpecification.paymentByDate(specificationDTO.getDate(), specificationDTO.getFromDate(), specificationDTO.getToDate()))
                        .and(com.clinic.pago_service.specification.PaymentSpecification.paymentByAmount(specificationDTO.getAmount(),
                                        specificationDTO.getFromAmount(),
                                        specificationDTO.getToAmount())
                                .and(com.clinic.pago_service.specification.PaymentSpecification.paymentByPatientId(specificationDTO.getPatientId()))
                                .and(com.clinic.pago_service.specification.PaymentSpecification.paymentByConsultationId(specificationDTO.getConsultationId())));

        Page<com.clinic.pago_service.Domain.payment.Payment> payments = paymentRepository.findAll(spec, pageable);

        if (payments.isEmpty()) {
            return Page.empty();
        }

        List<Long> consultationIds = payments.map(Payment::getConsultationId).getContent();
        List<com.clinic.pago_service.event.Model.Consultation> consultations = consultationRepository.findAllById(consultationIds);

        Map<Long, Pacientes> consultationPatientMap = consultations.stream()
                .collect(Collectors.toMap(Consultation::getId, Consultation::getPatient));

        return payments.map(payment -> {
            PaymentModel paymentModel = paymentMapper.toModel(payment);
            paymentModel.setPatient(consultationPatientMap.get(payment.getConsultationId()));
            return paymentModel;
        });
    }

    public PaymentModel getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found with id: " + id));
        return paymentMapper.toModel(payment);
    }

    public ResponseEntity<byte[]> getReceiptByPayment(Long paymentId) {
        Payment payment = paymentRepository.findByIdAndCanceled(paymentId, false)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found with id: " + paymentId));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("inline")
                .filename("receipt_"+payment.getId()+".pdf").build());

        com.clinic.pago_service.event.Model.Consultation consultation = consultationRepository.getConsultation(payment.getConsultationId());
        Pacientes patient = consultation.getPatient();
        List<MedicalServices> medicalServices = consultation.getMedicalServicesModel();
        Factura invoice = payment.getFactura();

        Map<String, Object> receiptData = new HashMap<>();
        receiptData.put("receiptNumber", String.format("%010d", payment.getId()));
        receiptData.put("invoiceNumber", String.format("%010d", invoice.getId()));
        receiptData.put("paymentDate", payment.getPaymentDate().toLocalDate());
        receiptData.put("patientName", patient.getName());
        receiptData.put("patientAddress", patient.getAddress().toString());
        receiptData.put("patientProvince", "La Plata");
        receiptData.put("patientDni", patient.getDni());
        receiptData.put("patientPhone", patient.getPhone());
        receiptData.put("patientEmail", patient.getEmail());
        receiptData.put("finalPrice", consultation.getFinalPrice());
        receiptData.put("services", medicalServices);
        receiptData.put("paymentMethod", payment.getPaymentMethod().getDisplayName());

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(exportService.getReceiptPDF(receiptData));
    }

    public ResponseEntity<byte[]> getInvoiceByConsultation(Long consultationId) {
        com.clinic.pago_service.Domain.payment.Payment payment = paymentRepository.findByConsultationId(consultationId)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found for consultation id: " + consultationId));

        return invoiceService.getInvoiceByPayment(payment.getId());
    }


    public List<com.clinic.pago_service.Domain.payment.Payment> getPaymentsByDate(LocalDate date) {
        return paymentRepository.findByPaymentDateBetweenAndCanceled(date.atStartOfDay(), date.atTime(23, 59, 59),
                false);
    }

}
