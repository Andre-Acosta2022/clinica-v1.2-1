package com.clinic.client_service.repository;

import com.clinic.client_service.domain.persona;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PersonaRepository extends JpaRepository<persona, Long>, JpaSpecificationExecutor<persona> {

    @Query("SELECT m FROM persona m WHERE m.id = :id AND m.deleted = false")
    Optional<persona> findByIdAndNotDeleted(Long id);

    @Query("SELECT m FROM persona m WHERE m.dni = :dni")
    Optional<persona> findByDni(String dni);

    List<persona> findAllByIdAndDeleted(Long id, boolean deleted);

    @Query("SELECT m FROM persona m WHERE m.id IN :ids AND m.deleted = false")
    List<persona> findAllByIdAndNotDeleted(Set<Long> ids);

    @Query("SELECT m FROM persona m WHERE m.deleted = false AND " +
            "LOWER(CONCAT(m.surname, ' ', m.name)) LIKE LOWER(CONCAT('%', :fullName, '%')) ")
    Page<persona> findAllByFullNameAndNotDeleted(@Param("fullName") String fullName, Pageable pageable);

    @Query("SELECT m FROM persona m WHERE m.deleted = false AND " +
            "(LOWER(m.surname) LIKE LOWER(CONCAT('%', :surname, '%')) " +
            "and LOWER(m.name) LIKE LOWER(CONCAT('%', :name, '%')))")
    Page<persona> findAllByNamesAndNotDeleted(@Param("surname") String surname, @Param("name") String name,
                                             Pageable pageable);
}
