package upeu.edu.pe.ms_paciente.controller;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upeu.edu.pe.ms_paciente.domain.Dto.CitaDTO;
import upeu.edu.pe.ms_paciente.domain.Dto.FacturaDTO;
import upeu.edu.pe.ms_paciente.domain.Dto.HistoriaClinicaDTO;
import upeu.edu.pe.ms_paciente.domain.Paciente;
import upeu.edu.pe.ms_paciente.service.PacienteService;
import upeu.edu.pe.ms_paciente.service.impl.PacienteServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;
    private PacienteServiceImpl service;

    @GetMapping
    public ResponseEntity<List<Paciente>> readAll() {
        try {
            List<Paciente> pacients = pacienteService.readAll();
            if(pacients.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(pacients, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PostMapping
    public ResponseEntity<Paciente> guardarPaciente(@Valid @RequestBody Paciente paci) {
        try {
            Paciente c = pacienteService.create(paci);
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getPacienteId(@PathVariable("id") Long id) {
        try {
            Paciente c = pacienteService.read(id).get();
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPaciente(@PathVariable("id") Long id) {
        try {
            pacienteService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePaciente(@PathVariable("id") Long id, @Valid @RequestBody Paciente paci){

        Optional<Paciente> c = pacienteService.read(id);
        if(!c.isEmpty()) {
            paci.setId(id); // Asigna el ID para evitar crear uno nuevo
            return new ResponseEntity<>(pacienteService.update(paci), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }
    /**
     dto cita
     * */
    @GetMapping("/{id}/citas")
    public ResponseEntity<List<CitaDTO>> obtenerCitasPaciente(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerCitasPaciente(id));
    }
    @GetMapping("/{id}/historia-clinica")
    public ResponseEntity<List<HistoriaClinicaDTO>> getHistoriaClinicaPaciente(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerHistoriaClinicaPaciente(id));
    }

    @GetMapping("/{id}/facturas")
    public ResponseEntity<List<FacturaDTO>> getFacturasPaciente(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerFacturasPaciente(id));
    }

}
