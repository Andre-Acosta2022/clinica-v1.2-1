package com.clinic.client_service.Service;
import com.clinic.client_service.Client.PacienteClient;
import com.clinic.client_service.domain.Dto.response.PacienteDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteFeignService {
    private static final String CIRCUIT_BREAKER_NAME = "pacienteService";

    private final PacienteClient pacienteClient;

    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "fallbackGetPacienteById")
    public PacienteDto
    getPacienteById(Long id) {
        return pacienteClient.getPacienteById(id);
    }

    public PacienteDto fallbackGetPacienteById(Long id, Throwable t) {
        // Aquí puedes devolver un objeto por defecto o null
        return null;
    }

    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "fallbackGetAllPacientes")
    public List<PacienteDto> getAllPacientes() {
        return pacienteClient.getAllPacientes();
    }

    public List<PacienteDto> fallbackGetAllPacientes(Throwable t) {
        return Collections.emptyList();
    }
}
