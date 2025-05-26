package com.clinic.incident_service.controllers;

import com.clinic.incident_service.domain.Turno;
import com.clinic.incident_service.repository.TurnoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turno")
public class TurnoController {
    @Autowired
    private TurnoRepository service;

    @GetMapping
    public ResponseEntity<List<Turno>> readAll() {
        try {
            List<Turno> turnos = service.findAll();
            if (turnos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(turnos, HttpStatus.OK);
        }   catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Turno> create(@Valid @RequestBody Turno m) {
        try {
            Turno tur= service.save(m);
            return new ResponseEntity<>(tur, HttpStatus.CREATED);
        }   catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> getTurnoId(@PathVariable("id") Long id) {
        try {
            Turno m = service.readById(id);
            return new ResponseEntity<>(m, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Turno> delTurnoId(@PathVariable("id") Long id) {
        try {
            service.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }   catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping ("/{id}")
    public ResponseEntity<?> updateTurno(@PathVariable("id") Long id, @Valid @RequestBody Turno tur) {
        Turno m = service.readById(id);
        if (m.getId()>0){
            return new ResponseEntity<>(service.save(tur), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
