package upeu.edu.pe.ms_paciente.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import upeu.edu.pe.ms_paciente.Generic.BaseController;
import upeu.edu.pe.ms_paciente.domain.Paciente;
import upeu.edu.pe.ms_paciente.service.BaseService;
import upeu.edu.pe.ms_paciente.service.PacienteService;
/*
@RestController
@RequestMapping("/pacientes")
public class PacienteControllers extends BaseController<Paciente, Long> {

    private final PacienteService pacienteService;

    public PacienteControllers(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @Override
    protected BaseService<Paciente, Long> getService() {
        return pacienteService;
    }

    // Aquí agregas métodos específicos para Paciente
    @GetMapping("/buscarPorDocumento/{dni}")
    public ResponseEntity<Paciente> buscarPorDocumento(@PathVariable String dni) {
        Paciente paciente = pacienteService.findByDocumento(dni);
        if (paciente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(paciente);
    }
}
*/