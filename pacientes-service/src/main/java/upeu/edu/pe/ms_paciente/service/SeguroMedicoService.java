package upeu.edu.pe.ms_paciente.service;
import upeu.edu.pe.ms_paciente.domain.Dto.SeguroMedicoDto;
import upeu.edu.pe.ms_paciente.domain.Dto.request.SeguroMedicoRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


import java.util.List;
import java.util.Optional;
@CircuitBreaker(name = "SeguroMedicoService", fallbackMethod = "FallBackFactory")
public interface SeguroMedicoService {

    SeguroMedicoDto create(SeguroMedicoRequest dto);

    SeguroMedicoDto update(Long id, SeguroMedicoRequest dto);

    void delete(Long id);

    Optional<SeguroMedicoDto> getById(Long id);

    List<SeguroMedicoDto> getAll();
}
