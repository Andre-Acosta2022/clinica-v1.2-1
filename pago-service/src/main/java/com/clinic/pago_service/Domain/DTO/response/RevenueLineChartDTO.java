package com.clinic.pago_service.Domain.DTO.response;

import java.math.BigDecimal;

public record RevenueLineChartDTO(
        String date,
        BigDecimal value
) {

}
