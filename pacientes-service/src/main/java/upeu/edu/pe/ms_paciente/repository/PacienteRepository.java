package upeu.edu.pe.ms_paciente.repository;

import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import upeu.edu.pe.ms_paciente.domain.Paciente;

import java.util.Collection;
import java.util.List;

public interface PacienteRepository extends JpaRepository<Paciente, Long>, JpaSpecificationExecutor<Paciente> {
    boolean existsByPersonIdAndDeletedFalse(Long personId);
}
