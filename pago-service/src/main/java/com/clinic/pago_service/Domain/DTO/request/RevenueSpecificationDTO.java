package com.clinic.pago_service.Domain.DTO.request;

import com.clinic.pago_service.Domain.revenue.RevenueType;
import com.clinic.pago_service.event.Model.medical_services.ServiceType;
import lombok.Data;

@Data
public class RevenueSpecificationDTO {
    private String date;
    private String fromDate;
    private String toDate;
    private RevenueType revenueType;
    private String specialityName;
    private ServiceType serviceType;
    private OriginName originName;
}
