package upeu.edu.pe.ms_paciente.service.impl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import upeu.edu.pe.ms_paciente.Mapper.SeguroMedicoMapper;
import upeu.edu.pe.ms_paciente.domain.Dto.SeguroMedicoDto;
import upeu.edu.pe.ms_paciente.domain.Dto.request.SeguroMedicoRequest;
import upeu.edu.pe.ms_paciente.domain.SeguroMedico;
import upeu.edu.pe.ms_paciente.repository.SeguroMedicoRepository;
import upeu.edu.pe.ms_paciente.service.SeguroMedicoService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SeguroMedicoServiceImpl implements SeguroMedicoService {

    private final SeguroMedicoRepository repository;
    private final SeguroMedicoMapper mapper;

    @Override
    @CircuitBreaker(name = "seguroMedicoService", fallbackMethod = "fallbackCreate")
    public SeguroMedicoDto create(SeguroMedicoRequest dto) {
        SeguroMedico entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDTO(entity);
    }

    public SeguroMedicoDto fallbackCreate(SeguroMedicoRequest dto, Throwable t) {
        return new SeguroMedicoDto();
    }

    @Override
    @CircuitBreaker(name = "seguroMedicoService", fallbackMethod = "fallbackUpdate")
    public SeguroMedicoDto update(Long id, SeguroMedicoRequest dto) {
        SeguroMedico existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SeguroMedico no encontrado con id: " + id));
        mapper.updateEntityFromDto(dto, existing);
        existing = repository.save(existing);
        return mapper.toDTO(existing);
    }

    public SeguroMedicoDto fallbackUpdate(Long id, SeguroMedicoRequest dto, Throwable t) {
        return new SeguroMedicoDto();
    }

    @Override
    @CircuitBreaker(name = "seguroMedicoService", fallbackMethod = "fallbackDelete")
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void fallbackDelete(Long id, Throwable t) {
        // No operation o loguear error si quieres
    }

    @Override
    @CircuitBreaker(name = "seguroMedicoService", fallbackMethod = "fallbackGetById")
    public Optional<SeguroMedicoDto> getById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    public Optional<SeguroMedicoDto> fallbackGetById(Long id, Throwable t) {
        return Optional.empty();
    }

    @Override
    @CircuitBreaker(name = "seguroMedicoService", fallbackMethod = "fallbackGetAll")
    public List<SeguroMedicoDto> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }

    public List<SeguroMedicoDto> fallbackGetAll(Throwable t) {
        return List.of();
    }
}