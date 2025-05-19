package upeu.edu.pe.ms_citas.Service;

import org.springframework.stereotype.Service;
import upeu.edu.pe.ms_citas.Domain.Dto.CitaDetalleDTO;
import upeu.edu.pe.ms_citas.Domain.citas;

import java.util.List;
import java.util.Optional;

@Service
public interface CitaService {
    citas create(citas c);
    citas update(citas c);
    void delete(Long id);
    Optional<citas> read(Long id);
    List<citas> readAll();
    List<citas> readByPaciente(Long pacienteId);
    List<citas> readByMedico(Long id);
    CitaDetalleDTO obtenerDetalle(Long id);
}
