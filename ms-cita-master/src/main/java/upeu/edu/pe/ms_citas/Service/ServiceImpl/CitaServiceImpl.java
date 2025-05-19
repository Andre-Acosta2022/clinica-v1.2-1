package upeu.edu.pe.ms_citas.Service.ServiceImpl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upeu.edu.pe.ms_citas.Client.MedicoClient;
import upeu.edu.pe.ms_citas.Client.PacienteClient;
import upeu.edu.pe.ms_citas.Domain.Dto.CitaDetalleDTO;
import upeu.edu.pe.ms_citas.Domain.Dto.MedicoDTO;
import upeu.edu.pe.ms_citas.Domain.Dto.PacienteDTO;
import upeu.edu.pe.ms_citas.Domain.citas;
import upeu.edu.pe.ms_citas.Repository.CitaRepository;
import upeu.edu.pe.ms_citas.Service.CitaService;

import java.util.List;
import java.util.Optional;
@Data
@Service
@RequiredArgsConstructor
public class CitaServiceImpl implements CitaService {

    @Autowired
    private  CitaRepository citaRepository;
    @Autowired
    private PacienteClient pacienteClient;
    @Autowired
    private MedicoClient medicoClient;
    @Override
    public citas create(citas c) {
        return citaRepository.save(c);
    }

    @Override
    public citas update(citas c) {
        return citaRepository.save(c);
    }

    @Override
    public void delete(Long id) {
        citaRepository.deleteById(id);

    }

    @Override
    public Optional<citas> read(Long id) {
        return citaRepository.findById(id);
    }

    @Override
    public List<citas> readAll() {
        return citaRepository.findAll();
    }

    @Override
    public List<citas> readByPaciente(Long pacienteId) {
        return citaRepository.findByPacienteId(pacienteId);
    }

    @Override
    public List<citas> readByMedico(Long medicoId) {
        return citaRepository.findByMedicoId(medicoId);
    }

    @Override
    public CitaDetalleDTO obtenerDetalle(Long id) {
        citas cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        PacienteDTO paciente = pacienteClient.getPacientePorId(cita.getPacienteId());
        MedicoDTO medico = medicoClient.getMedicoPorId(cita.getMedicoId());

        CitaDetalleDTO detalle = new CitaDetalleDTO();
        detalle.setId(cita.getId());
        detalle.setFechaHora(cita.getFechaHora());
        detalle.setMotivo(cita.getMotivo());
        detalle.setEstado(cita.getEstado());
        detalle.setPaciente(paciente);
        detalle.setMedico(medico);
        return detalle;
    }
}
