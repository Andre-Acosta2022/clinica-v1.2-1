package com.clinic.client_service.repository;

import com.clinic.client_service.domain.Persona;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PersonaRepository extends JpaRepository<Persona, Long>, JpaSpecificationExecutor<Persona> {
    @Query("SELECT p FROM Persona p WHERE p.id = :id AND p.deleted = false")
    Optional<Persona> findByIdAndNotDeleted(@Param("id") Long id);

    @Query("SELECT p FROM Persona p WHERE p.dni = :dni")
    Optional<Persona> findByDni(@Param("dni") String dni);

    @Query("SELECT p FROM Persona p WHERE p.id IN :ids AND p.deleted = false")
    List<Persona> findAllByIdAndNotDeleted(@Param("ids") Set<Long> ids);

    @Query("SELECT p FROM Persona p WHERE p.deleted = false AND LOWER(CONCAT(p.surname, ' ', p.name)) LIKE LOWER(CONCAT('%', :fullName, '%'))")
    Page<Persona> findAllByFullNameAndNotDeleted(@Param("fullName") String fullName, Pageable pageable);

    @Query("SELECT p FROM Persona p WHERE p.deleted = false AND LOWER(p.surname) LIKE LOWER(CONCAT('%', :surname, '%')) AND LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Persona> findAllByNamesAndNotDeleted(@Param("surname") String surname, @Param("name") String name, Pageable pageable);
}
