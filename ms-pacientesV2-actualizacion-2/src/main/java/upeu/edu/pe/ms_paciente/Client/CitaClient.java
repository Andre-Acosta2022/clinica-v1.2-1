package upeu.edu.pe.ms_paciente.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import upeu.edu.pe.ms_paciente.domain.Dto.CitaDTO;

import java.util.List;

@FeignClient(name = "ms-citas")
public interface CitaClient {
    @GetMapping("/api/citas/paciente/{id}")
    List<CitaDTO> obtenerCitasPorPaciente(@PathVariable("id") Long id);
}
