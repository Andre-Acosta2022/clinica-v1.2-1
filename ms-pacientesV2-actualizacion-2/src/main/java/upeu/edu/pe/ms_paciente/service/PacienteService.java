package upeu.edu.pe.ms_paciente.service;
import org.springframework.stereotype.Service;
import upeu.edu.pe.ms_paciente.domain.Dto.CitaDTO;
import upeu.edu.pe.ms_paciente.domain.Dto.FacturaDTO;
import upeu.edu.pe.ms_paciente.domain.Dto.HistoriaClinicaDTO;
import upeu.edu.pe.ms_paciente.domain.Paciente;


import java.util.List;
import java.util.Optional;

@Service
public interface PacienteService {
    Paciente create(Paciente c);
    Paciente update(Paciente c);
    void delete(Long id);
    Optional<Paciente> read(Long id);
    List<Paciente> readAll();
    List<CitaDTO> obtenerCitasPaciente(Long id);
    List<HistoriaClinicaDTO> obtenerHistoriaClinicaPaciente(Long id);
    List<FacturaDTO> obtenerFacturasPaciente(Long id);
}
