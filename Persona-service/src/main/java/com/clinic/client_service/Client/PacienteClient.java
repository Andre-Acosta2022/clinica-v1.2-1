package com.clinic.client_service.Client;

import com.clinic.client_service.domain.Dto.response.PacienteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "pacientes-service", configuration = com.clinic.client_service.configuration.FeignConfig.class, fallbackFactory = com.clinic.client_service.Client.FallBack.PacienteClientFallbackFactory.class)
public interface PacienteClient {

    @GetMapping("/api/pacientes/{id}")
    PacienteDto getPacienteById(@PathVariable("id") Long id);

    @GetMapping("/api/pacientes")
    List<PacienteDto> getAllPacientes();
}
