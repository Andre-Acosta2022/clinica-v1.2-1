package upeu.edu.pe.ms_paciente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import upeu.edu.pe.ms_paciente.domain.SeguroMedico;

public interface SeguroMedicoRepository extends JpaRepository<SeguroMedico, Long>, JpaSpecificationExecutor<SeguroMedico> {
}
