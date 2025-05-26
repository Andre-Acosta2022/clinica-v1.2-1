package com.clinic.pago_service.Controller;


import com.clinic.pago_service.Domain.DTO.request.CreatePaymentDTO;

import com.clinic.pago_service.service.PaymentService;
import lombok.AllArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@AllArgsConstructor
@Controller
@RequestMapping("/api/payments")

public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping(produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> createPayment(@RequestBody CreatePaymentDTO paymentDTO) {
        return paymentService.createPayment(paymentDTO);
    }

    @GetMapping(path = "/{id}/receipt", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getReceiptByPayment(@PathVariable Long id) {
        return paymentService.getReceiptByPayment(id);
    }

    @GetMapping(path = "/invoice/consultation/{consultationId}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getInvoiceByConsultation(@PathVariable Long consultationId) {
        return paymentService.getInvoiceByConsultation(consultationId);
    }
}