package com.clinic.incident_service.controllers;

import com.example.msmedico.domain.Medico;
import com.example.msmedico.repository.MedicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository service;

    @GetMapping
    public ResponseEntity<List<Medico>> readAll() {
        try {
            List<Medico> medicos = service.findAll();
            if (medicos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(medicos, HttpStatus.OK);
        }   catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Medico> create(@Valid @RequestBody Medico m) {
        try {
            Medico med= service.save(m);
            return new ResponseEntity<>(med, HttpStatus.CREATED);
        }   catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> getMedicoId(@PathVariable("id") Long id) {
        try {
            Medico m = service.readById(id);
            return new ResponseEntity<>(m, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Medico> delMedicoId(@PathVariable("id") Long id) {
        try {
            service.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }   catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping ("/{id}")
    public ResponseEntity<?> updateMedico(@PathVariable("id") Long id, @Valid @RequestBody Medico med) {
        Medico m = service.readById(id);
        if (m.getId()>0){
            return new ResponseEntity<>(service.save(med), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
