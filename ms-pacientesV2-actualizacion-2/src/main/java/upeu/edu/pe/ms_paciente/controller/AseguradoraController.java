package upeu.edu.pe.ms_paciente.controller;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upeu.edu.pe.ms_paciente.domain.aseguradora;
import upeu.edu.pe.ms_paciente.service.AseguradoraService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/aseguradoras")
public class AseguradoraController {

    @Autowired
    private AseguradoraService aseguradoraService;

    @GetMapping
    public ResponseEntity<List<aseguradora>> readAll() {
        try {
            List<aseguradora> aseguradoras = aseguradoraService.readAll();
            if (aseguradoras.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(aseguradoras, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<aseguradora> guardarAseguradora(@Valid @RequestBody aseguradora asegur) {
        try {
            aseguradora a = aseguradoraService.create(asegur);
            return new ResponseEntity<>(a, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<aseguradora> getAseguradoraById(@PathVariable("id") Long id) {
        try {
            aseguradora a = aseguradoraService.read(id).get();
            return new ResponseEntity<>(a, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAseguradora(@PathVariable("id") Long id) {
        try {
            aseguradoraService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAseguradora(@PathVariable("id") Long id, @Valid @RequestBody aseguradora asegu) {
        Optional<aseguradora> a = aseguradoraService.read(id);
        if (a.isEmpty()) {
            asegu.setId(id); // Asigna el ID para evitar crear uno nuevo
            return new ResponseEntity<>(aseguradoraService.update(asegu), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
