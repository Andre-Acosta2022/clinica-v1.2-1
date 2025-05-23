package upeu.edu.pe.ms_paciente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upeu.edu.pe.ms_paciente.domain.SeguroMedico;

public interface AseguradoraRepository extends JpaRepository<SeguroMedico, Long> {
}
