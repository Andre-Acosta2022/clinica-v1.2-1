package com.clinic.client_service.Controller;
import com.clinic.client_service.Service.PacienteFeignService;
import com.clinic.client_service.domain.Dto.response.PacienteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/paciente-feign")
@RequiredArgsConstructor
public class PacienteFeignController {
    private final PacienteFeignService pacienteFeignService;

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDto> getPaciente(@PathVariable Long id) {
        PacienteDto paciente = pacienteFeignService.getPacienteById(id);
        if (paciente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(paciente);
    }

    @GetMapping
    public ResponseEntity<List<PacienteDto>> getAllPacientes() {
        List<PacienteDto> pacientes = pacienteFeignService.getAllPacientes();
        return ResponseEntity.ok(pacientes);
    }
}
