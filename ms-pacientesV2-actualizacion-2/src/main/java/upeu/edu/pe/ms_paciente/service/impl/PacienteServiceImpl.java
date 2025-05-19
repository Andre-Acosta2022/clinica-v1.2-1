package upeu.edu.pe.ms_paciente.service.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upeu.edu.pe.ms_paciente.Client.CitaClient;
import upeu.edu.pe.ms_paciente.Client.FacturaClient;
import upeu.edu.pe.ms_paciente.Client.HCClient;
import upeu.edu.pe.ms_paciente.domain.Dto.CitaDTO;
import upeu.edu.pe.ms_paciente.domain.Dto.FacturaDTO;
import upeu.edu.pe.ms_paciente.domain.Dto.HistoriaClinicaDTO;
import upeu.edu.pe.ms_paciente.domain.Paciente;
import upeu.edu.pe.ms_paciente.repository.PacienteRepository;
import upeu.edu.pe.ms_paciente.service.PacienteService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository repository;

    @Autowired
    private CitaClient citaClient;

    @Autowired
    private HCClient hcClient;

    @Autowired
    private FacturaClient facturaClient;

    @Override
    public Paciente create(Paciente c) {
        return repository.save(c);
    }

    @Override
    public Paciente update(Paciente c) {
        return repository.save(c);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Paciente> read(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Paciente> readAll() {
        return repository.findAll();
    }

    @Override
    @CircuitBreaker(name = "citaService", fallbackMethod = "fallbackCitas")
    public List<CitaDTO> obtenerCitasPaciente(Long id) {
        return citaClient.obtenerCitasPorPaciente(id);
    }

    public List<CitaDTO> fallbackCitas(Long id, Throwable t) {
        // Retorna lista vacía o manejo alternativo en caso de fallo
        return Collections.emptyList();
    }

    @Override
    @CircuitBreaker(name = "hcService", fallbackMethod = "fallbackHistoriaClinica")
    public List<HistoriaClinicaDTO> obtenerHistoriaClinicaPaciente(Long id) {
        return hcClient.obtenerHistoriaClinicaPorPaciente(id);
    }

    public List<HistoriaClinicaDTO> fallbackHistoriaClinica(Long id, Throwable t) {
        return Collections.emptyList();
    }

    @Override
    @CircuitBreaker(name = "facturaService", fallbackMethod = "fallbackFacturas")
    public List<FacturaDTO> obtenerFacturasPaciente(Long id) {
        return facturaClient.obtenerFacturasPorPaciente(id);
    }

    public List<FacturaDTO> fallbackFacturas(Long id, Throwable t) {
        return Collections.emptyList();
    }
}
