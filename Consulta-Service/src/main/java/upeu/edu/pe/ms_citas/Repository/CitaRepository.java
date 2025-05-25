package upeu.edu.pe.ms_citas.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upeu.edu.pe.ms_citas.Domain.citas;

import java.util.List;

public interface CitaRepository extends JpaRepository<citas, Long> {
    List<citas> findByPacienteId(Long pacienteId);
    List<citas> findByMedicoId(Long medicoId);
}
