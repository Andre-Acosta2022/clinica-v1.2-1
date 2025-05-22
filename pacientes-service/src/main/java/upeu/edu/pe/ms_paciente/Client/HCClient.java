package upeu.edu.pe.ms_paciente.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import upeu.edu.pe.ms_paciente.domain.Dto.HistoriaClinicaDTO;

import java.util.List;

@FeignClient(name = "ms-hc")
public interface HCClient {

    @GetMapping("/api/hc/paciente/{id}")
    List<HistoriaClinicaDTO> obtenerHistoriaClinicaPorPaciente(@PathVariable("id") Long id);
}