package upeu.edu.pe.ms_citas.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import upeu.edu.pe.ms_citas.Domain.Dto.MedicoDTO;

import java.util.List;

@FeignClient(name = "ms-medicos")
public interface MedicoClient {
    @GetMapping("/api/medicos/{id}")
    MedicoDTO getMedicoPorId(@PathVariable("id") Long id);
}
