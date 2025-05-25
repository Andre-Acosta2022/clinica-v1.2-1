package upeu.edu.pe.ms_paciente.service;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import upeu.edu.pe.ms_paciente.domain.Dto.PacienteDto;
import upeu.edu.pe.ms_paciente.domain.Dto.request.PacienteRequest;

@CircuitBreaker(name = "PacienteService", fallbackMethod = "FallBackFactory")
public interface PacienteService {

    PacienteDto getById(Long id);

    Page<PacienteDto> getAll(Pageable pageable, String filter);

    PacienteDto create(PacienteRequest dto);

    PacienteDto update(Long id, PacienteRequest dto);

    boolean delete(Long id);
}
