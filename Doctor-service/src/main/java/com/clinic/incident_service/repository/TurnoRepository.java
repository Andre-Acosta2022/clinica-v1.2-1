package com.clinic.incident_service.repository;
import com.clinic.incident_service.domain.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurnoRepository extends JpaRepository<Turno, Long> {
    Turno readById(Long id);
}
