package com.clinic.pago_service.event.Model;

import com.clinic.pago_service.event.Model.medical_services.MedicalServices;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Consultation {
    Long id;
    Pacientes patient;
    BigDecimal finalPrice;
    List<MedicalServices> medicalServicesModel;

    public Consultation(Long id) {
        this.id = id;
    }
}
