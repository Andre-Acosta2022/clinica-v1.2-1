package upeu.edu.pe.ms_citas.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upeu.edu.pe.ms_citas.Domain.Dto.CitaDetalleDTO;
import upeu.edu.pe.ms_citas.Domain.citas;
import upeu.edu.pe.ms_citas.Service.CitaService;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
@RequiredArgsConstructor
public class CitaController {

    private final CitaService citaService;

    @GetMapping
    public ResponseEntity<List<citas>> readAll() {
        try {
            List<citas> cita = citaService.readAll();
            if(cita.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cita, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<citas> obtenerPorId(@PathVariable("id") Long id) {
        try {
            citas c =citaService.read(id).get();
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        }catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<CitaDetalleDTO> obtenerDetalle(@PathVariable("id") Long id) {
        try {
            CitaDetalleDTO detalle = citaService.obtenerDetalle(id);
            return ResponseEntity.ok(detalle);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/paciente/{id}")
    public List<citas> listarPorPaciente(@PathVariable Long id) {
        return citaService.readByPaciente(id);
    }

    @GetMapping("/medico/{medicoId}")
    public List<citas> listarPorMedico(@PathVariable Long medicoId) {
        return citaService.readByMedico(medicoId);
    }

    @PostMapping
    public ResponseEntity<citas> guardar(@Valid @RequestBody citas cita) {
        try {
            citas c = citaService.create(cita);
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

@DeleteMapping("/{id}")
public ResponseEntity<Void> eliminarCita(@PathVariable("id") Long id) {
    try {
        citaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
        // TODO: handle exception
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
}