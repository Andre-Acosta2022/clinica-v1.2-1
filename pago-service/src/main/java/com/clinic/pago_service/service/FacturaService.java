package com.clinic.pago_service.service;

import com.clinic.pago_service.Domain.Factura;
import com.clinic.pago_service.Domain.payment.Payment;
import com.clinic.pago_service.event.Model.Consultation;
import com.clinic.pago_service.event.Model.Pacientes;
import com.clinic.pago_service.event.Model.medical_services.MedicalServices;
import com.clinic.pago_service.event.Model.medical_services.MedicalServicesWrapped;
import com.clinic.pago_service.repository.ConsultationRepository;
import com.clinic.pago_service.repository.FacturaRepository;
import com.clinic.pago_service.repository.PacienteRepository;
import com.clinic.pago_service.repository.ServicioMedicoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@AllArgsConstructor
@Service
public class FacturaService {

    private final FacturaRepository invoiceRepository;
    private final PacienteRepository patientRepository;
    private final ConsultationRepository consultationRepository;
    private final ExportService exportService;
    private final ServicioMedicoRepository medicalServicesRepository;



    public Factura createInvoice(Payment payment) {
        Consultation consultation = consultationRepository.getConsultation(payment.getConsultationId());
        Pacientes patient = consultation.getPatient();
        List<MedicalServices> medicalServices = consultation.getMedicalServicesModel();

        Factura invoice = new Factura();
        invoice.setPatientId(patient.getId());
        invoice.setTotalAmount(consultation.getFinalPrice());
        invoice.setInvoiceDate(LocalDate.now());
        invoice.setPayment(payment);

        List<Long> medicalServicesId = new ArrayList<>();
        for (MedicalServices medicalService : medicalServices) {
            medicalServicesId.add(medicalService.getId());
        }
        invoice.setMedicalServicesId(medicalServicesId);

        return invoiceRepository.save(invoice);
    }

    public ResponseEntity<byte[]> getInvoice(Long invoiceId) {
        Factura invoice = invoiceRepository.findById(invoiceId).orElseThrow(() -> new EntityNotFoundException("Invoice not found for id: " + invoiceId));
        return generateInvoice(invoice);
    }


    public ResponseEntity<byte[]> getInvoiceByPayment(Long paymentId) {
        Factura invoice = invoiceRepository.findByPaymentId(paymentId).orElseThrow(() -> new EntityNotFoundException("Invoice not found for payment id: " + paymentId));
        return generateInvoice(invoice);
    }

    private ResponseEntity<byte[]> generateInvoice( Factura invoice) {
        Pacientes patient = patientRepository.getPatientById(invoice.getPatientId());
        Set<Long> medicalServicesId = new HashSet<>(invoice.getMedicalServicesId());
        List<MedicalServicesWrapped> medicalServicesWrappeds = medicalServicesRepository.findAllById(medicalServicesId);
        List<MedicalServices> medicalServices = medicalServicesWrappeds.stream().map(MedicalServicesWrapped::getMedicalServices).toList();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("inline")
                .filename("invoice_" + invoice.getPayment().getId() + ".pdf").build());


        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(exportService.getInvoicePDF(this.getInvoiceData(invoice, patient, medicalServices)));
    }

    private Map<String, Object> getInvoiceData(Factura invoice, Pacientes patient, List<MedicalServices> medicalServices) {
        Map<String, Object> invoiceData = new HashMap<>();
        invoiceData.put("invoiceNumber", String.format("%010d", invoice.getId()));
        invoiceData.put("invoiceDate", invoice.getInvoiceDate());
        invoiceData.put("patientName", patient.getName());
        invoiceData.put("patientAddress", patient.getAddress().toString());
        invoiceData.put("patientProvince", "La Plata");
        invoiceData.put("patientDni", patient.getDni());
        invoiceData.put("patientPhone", patient.getPhone());
        invoiceData.put("patientEmail", patient.getEmail());
        invoiceData.put("finalPrice", invoice.getTotalAmount().floatValue());
        invoiceData.put("services", medicalServices);
        invoiceData.put("paymentMethod", invoice.getPayment().getPaymentMethod().getDisplayName());
        return invoiceData;
    }
}
