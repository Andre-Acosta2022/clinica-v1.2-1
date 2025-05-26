package com.clinic.pago_service.Domain.DTO.response;

import java.math.BigDecimal;

public record RevenuePieChartDTO(
        String name,
        BigDecimal value
) {
}
