package upeu.edu.pe.ms_paciente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upeu.edu.pe.ms_paciente.domain.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
