package upeu.edu.pe.ms_paciente.controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upeu.edu.pe.ms_paciente.domain.Dto.SeguroMedicoDto;
import upeu.edu.pe.ms_paciente.domain.Dto.request.SeguroMedicoRequest;
import upeu.edu.pe.ms_paciente.domain.SeguroMedico;
import upeu.edu.pe.ms_paciente.service.SeguroMedicoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/aseguradoras")
@RequiredArgsConstructor
public class AseguradoraController {
    private final SeguroMedicoService seguroMedicoService;

    @GetMapping("/{id}")
    public ResponseEntity<SeguroMedicoDto> getById(@PathVariable Long id) {
        return seguroMedicoService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<SeguroMedicoDto>> getAll() {
        List<SeguroMedicoDto> lista = seguroMedicoService.getAll();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<SeguroMedicoDto> create(@Valid @RequestBody SeguroMedicoRequest dto) {
        SeguroMedicoDto creado = seguroMedicoService.create(dto);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeguroMedicoDto> update(@PathVariable Long id,
                                                  @Valid @RequestBody SeguroMedicoRequest dto) {
        SeguroMedicoDto actualizado = seguroMedicoService.update(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        seguroMedicoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
