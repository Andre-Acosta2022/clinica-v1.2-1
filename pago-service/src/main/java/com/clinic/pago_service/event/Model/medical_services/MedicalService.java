package com.clinic.pago_service.event.Model.medical_services;

import com.clinic.pago_service.event.Model.Speciality;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MedicalService implements MedicalServices{
    private Long id;
    private BigDecimal price;
    private String name;
    private String code;
    private Speciality speciality;
    private ServiceType serviceType;


    public MedicalService(String code, BigDecimal price, String name) {
        this.code = code;
        this.price = price;
        this.name = name;
    }
}

