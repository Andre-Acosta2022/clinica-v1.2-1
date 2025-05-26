package com.clinic.pago_service.event;

import com.clinic.pago_service.event.Model.medical_services.ServiceType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentEvent(
        BigDecimal amount,
        LocalDateTime date,
        ServiceType serviceType,
        String specialityName
) {

}
