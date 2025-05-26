package com.clinic.pago_service.event.Model;

import com.clinic.pago_service.Domain.payment.PaymentMethod;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class PaymentModel {
    private Long id;
    private BigDecimal amount;
    private String paymentDate;
    private Boolean canceled;
    private Pacientes patient;
    private PaymentMethod paymentMethod;
    private Consultation consultation;
}
