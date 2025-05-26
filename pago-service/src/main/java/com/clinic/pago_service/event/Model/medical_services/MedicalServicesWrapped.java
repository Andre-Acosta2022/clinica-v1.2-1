package com.clinic.pago_service.event.Model.medical_services;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

@Setter
public class MedicalServicesWrapped {
    @JsonProperty("medicalServiceModel")
    private MedicalService medicalService;

    @JsonProperty("medicalPackageModel")
    private MedicalPackage medicalPackage;


    public MedicalServices getMedicalServices() {
        if (medicalService != null) {
            return medicalService;
        } else {
            return medicalPackage;
        }
    }
}
