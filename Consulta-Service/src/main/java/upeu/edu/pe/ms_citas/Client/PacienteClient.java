package upeu.edu.pe.ms_citas.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import upeu.edu.pe.ms_citas.Domain.Dto.PacienteDTO;

@FeignClient(name = "ms-paciente")
public interface PacienteClient {
    @GetMapping("/api/pacientes/{id}")
    PacienteDTO getPacientePorId(@PathVariable("id") Long id);
}
