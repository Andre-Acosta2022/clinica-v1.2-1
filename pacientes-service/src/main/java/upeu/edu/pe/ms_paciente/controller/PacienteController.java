package upeu.edu.pe.ms_paciente.controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upeu.edu.pe.ms_paciente.domain.Dto.PacienteDto;
import upeu.edu.pe.ms_paciente.domain.Dto.request.PacienteRequest;
import upeu.edu.pe.ms_paciente.domain.Paciente;
import upeu.edu.pe.ms_paciente.service.PacienteService;
import upeu.edu.pe.ms_paciente.service.impl.PacienteServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pacientes")
@RequiredArgsConstructor
public class PacienteController {
    private final PacienteService pacienteService;

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDto> getPacienteById(@PathVariable Long id) {
        PacienteDto paciente = pacienteService.getById(id);
        return ResponseEntity.ok(paciente);
    }

    @GetMapping
    public ResponseEntity<Page<PacienteDto>> getPacientes(Pageable pageable,
                                                          @RequestParam(required = false) String filter) {
        Page<PacienteDto> pacientes = pacienteService.getAll(pageable, filter);
        return ResponseEntity.ok(pacientes);
    }

    @PostMapping
    public ResponseEntity<PacienteDto> createPaciente(@Valid @RequestBody PacienteRequest dto) {
        PacienteDto creado = pacienteService.create(dto);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDto> updatePaciente(@PathVariable Long id,
                                                      @Valid @RequestBody PacienteRequest dto) {
        PacienteDto actualizado = pacienteService.update(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable Long id) {
        boolean eliminado = pacienteService.delete(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
