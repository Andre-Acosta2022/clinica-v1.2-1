package com.clinic.pago_service.repository;

import com.clinic.pago_service.event.Model.Pacientes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "patient-sv")
public interface PacienteRepository {
    @GetMapping("/api/patients/{id}")
    Pacientes getPatientById(@PathVariable Long id);
}
