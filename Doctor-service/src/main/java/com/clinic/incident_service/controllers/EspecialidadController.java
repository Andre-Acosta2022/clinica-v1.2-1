package com.clinic.incident_service.controllers;

import com.clinic.incident_service.domain.Especialidad;
import com.clinic.incident_service.repository.EspecialidadRepository;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especialidad")
public class EspecialidadController {

    @Autowired
    private EspecialidadRepository service;

    @GetMapping
    public ResponseEntity<List<Especialidad>> readAll() {
        try {
            List<Especialidad> especialidades = service.findAll();
            if (especialidades.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(especialidades, HttpStatus.OK);
        }   catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Especialidad> create(@Valid @RequestBody Especialidad m) {
        try {
            Especialidad med= service.save(m);
            return new ResponseEntity<>(med, HttpStatus.CREATED);
        }   catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especialidad> getEspecialidadId(@PathVariable("id") Long id) {
        try {
            Especialidad m = service.readById(id);
            return new ResponseEntity<>(m, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Especialidad> delEspecialidadId(@PathVariable("id") Long id) {
        try {
            service.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }   catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping ("/{id}")
    public ResponseEntity<?> updateEspecialidad(@PathVariable("id") Long id, @Valid @RequestBody Especialidad esp) {
        Especialidad m = service.readById(id);
        if (m.getId()>0){
            return new ResponseEntity<>(service.save(esp), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
