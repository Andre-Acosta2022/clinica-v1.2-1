package upeu.edu.pe.ms_paciente.service.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import upeu.edu.pe.ms_paciente.Client.PersonaClient;
import upeu.edu.pe.ms_paciente.Mapper.PacienteMapper;
import upeu.edu.pe.ms_paciente.domain.Dto.PacienteDto;
import upeu.edu.pe.ms_paciente.domain.Dto.request.PacienteRequest;
import upeu.edu.pe.ms_paciente.domain.Paciente;
import upeu.edu.pe.ms_paciente.repository.PacienteRepository;
import upeu.edu.pe.ms_paciente.service.PacienteService;
import upeu.edu.pe.ms_paciente.specificacion.pacienteSpecificacion;


@Service
@RequiredArgsConstructor
@Transactional
public class PacienteServiceImpl implements PacienteService {
    private final PacienteRepository repository;
    private final PacienteMapper mapper;
    private final PersonaClient personaClient; // Si llamas otro microservicio

    @Override
    @CircuitBreaker(name = "personaService", fallbackMethod = "fallbackGetById")
    public PacienteDto getById(Long id) {
        // Ejemplo si llamas microservicio externo persona
        // PersonaDto persona = personaClient.findById(id);

        Paciente paciente = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado con id: " + id));
        return mapper.toDTO(paciente);
    }

    public PacienteDto fallbackGetById(Long id, Throwable t) {
        // Devuelve DTO vacío o datos por defecto si falla el servicio
        return new PacienteDto();
    }

    @Override
    @CircuitBreaker(name = "personaService", fallbackMethod = "fallbackGetAll")
    public Page<PacienteDto> getAll(Pageable pageable, String filter) {
        Page<Paciente> pacientes = repository.findAll(
                Specification.where(pacienteSpecificacion.deletedEqual(false)), pageable);
        return pacientes.map(mapper::toDTO);
    }

    public Page<PacienteDto> fallbackGetAll(Pageable pageable, String filter, Throwable t) {
        return Page.empty(pageable);
    }

    @Override
    @CircuitBreaker(name = "personaService", fallbackMethod = "fallbackCreate")
    public PacienteDto create(PacienteRequest dto) {
        Paciente paciente = mapper.toEntity(dto);
        paciente = repository.save(paciente);
        return mapper.toDTO(paciente);
    }

    public PacienteDto fallbackCreate(PacienteRequest dto, Throwable t) {
        return new PacienteDto();
    }

    @Override
    @CircuitBreaker(name = "personaService", fallbackMethod = "fallbackUpdate")
    public PacienteDto update(Long id, PacienteRequest dto) {
        Paciente pacienteExistente = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado con id: " + id));
        mapper.updateEntityFromDto(dto, pacienteExistente);
        pacienteExistente = repository.save(pacienteExistente);
        return mapper.toDTO(pacienteExistente);
    }

    public PacienteDto fallbackUpdate(Long id, PacienteRequest dto, Throwable t) {
        return new PacienteDto();
    }

    @Override
    @CircuitBreaker(name = "personaService", fallbackMethod = "fallbackDelete")
    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean fallbackDelete(Long id, Throwable t) {
        return false;
    }
}
