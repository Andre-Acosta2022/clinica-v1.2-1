package upeu.edu.pe.ms_paciente.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import upeu.edu.pe.ms_paciente.domain.Dto.FacturaDTO;

import java.util.List;

@FeignClient(name = "ms-facturacion")
public interface FacturaClient {

    @GetMapping("/api/facturas/paciente/{id}")
    List<FacturaDTO> obtenerFacturasPorPaciente(@PathVariable("id") Long id);
}