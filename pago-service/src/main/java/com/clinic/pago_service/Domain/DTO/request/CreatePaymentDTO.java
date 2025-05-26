package com.clinic.pago_service.Domain.DTO.request;

import com.clinic.pago_service.Domain.payment.PaymentMethod;

public record CreatePaymentDTO(
  Long consultationId,
     PaymentMethod paymentMethod
){
}