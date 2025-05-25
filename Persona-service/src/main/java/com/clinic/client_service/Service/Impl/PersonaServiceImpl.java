package com.clinic.client_service.Service.Impl;

import com.clinic.client_service.Service.DireccionService;
import com.clinic.client_service.Service.PersonaService;
import com.clinic.client_service.domain.Dto.request.create.crearPersonaDto;
import com.clinic.client_service.domain.Dto.request.update.UpdatePersonaDTO;
import com.clinic.client_service.domain.Dto.response.GetPersonaDTO;
import com.clinic.client_service.domain.Persona;
import com.clinic.client_service.mapper.PersonaMapper;
import com.clinic.client_service.repository.PersonaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class PersonaServiceImpl implements PersonaService {
@Autowired
    private final PersonaRepository personRepository;
    private final PersonaMapper personMapper;
    private final DireccionService direccionService;

    @Override
    public ResponseEntity<GetPersonaDTO> getPersonById(Long id) {
        Persona person = personRepository.findByIdAndNotDeleted(id)
                .orElseThrow(() -> new EntityNotFoundException("Person not found with id: " + id));
        return ResponseEntity.ok(personMapper.toDTO(person));
    }

    @Override
    public ResponseEntity<GetPersonaDTO> createPerson(crearPersonaDto dto) {
        Persona person = personMapper.toEntity(dto);
        person.setDireccion(direccionService.createAddress(dto.getDireccion()));
        person.setBirthdate(LocalDate.parse(dto.getBirthdate()));
        personRepository.save(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(personMapper.toDTO(person));
    }

    @Override
    public ResponseEntity<GetPersonaDTO> updatePerson(Long id, UpdatePersonaDTO dto) {
        Persona person = personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Person not found with id: " + id));
        personMapper.updateEntity(person, dto);

        if (dto.direccion() != null) {
            person.setDireccion(direccionService.createAddress(dto.direccion()));
        }
        Persona updated = personRepository.save(person);
        return ResponseEntity.ok(personMapper.toDTO(updated));
    }

    @Override
    public ResponseEntity<Void> deletePerson(Long id) {
        Persona person = personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Person not found with id: " + id));
        person.setDeleted(true);
        personRepository.save(person);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<GetPersonaDTO> getPersonByDni(String dni) {
        Persona person = personRepository.findByDni(dni)
                .orElseThrow(() -> new EntityNotFoundException("Person not found with dni: " + dni));
        return ResponseEntity.ok(personMapper.toDTO(person));
    }

    @Override
    public ResponseEntity<List<GetPersonaDTO>> getPersonsByIds(Set<Long> ids) {
        List<Persona> persons = personRepository.findAllByIdAndNotDeleted(ids);
        List<GetPersonaDTO> dtos = persons.stream().map(personMapper::toDTO).toList();
        return ResponseEntity.ok(dtos);
    }

    @Override
    public ResponseEntity<Page<GetPersonaDTO>> getPersonsByFullName(String fullName, org.springframework.data.domain.Pageable pageable) {
        Page<Persona> persons = personRepository.findAllByFullNameAndNotDeleted(fullName, pageable);
        Page<GetPersonaDTO> dtos = persons.map(personMapper::toDTO);
        return ResponseEntity.ok(dtos);
    }

    @Override
    public ResponseEntity<List<GetPersonaDTO>> getAllPersons(String fullName, String name, String surname, String dni, com.clinic.client_service.domain.Genero gender) {
        Specification<Persona> spec = Specification.where(null);

        if (fullName != null) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.concat(cb.concat(root.get("surname"), " "), root.get("name")), "%" + fullName + "%"));
        }
        if (name != null) {
            spec = spec.and((root, query, cb) -> cb.like(root.get("name"), "%" + name + "%"));
        }
        if (surname != null) {
            spec = spec.and((root, query, cb) -> cb.like(root.get("surname"), "%" + surname + "%"));
        }
        if (dni != null) {
            spec = spec.and((root, query, cb) -> cb.like(root.get("dni"), "%" + dni + "%"));
        }
        if (gender != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("genero"), gender));
        }

        spec = spec.and((root, query, cb) -> cb.equal(root.get("deleted"), false));

        List<Persona> persons = personRepository.findAll(spec);
        List<GetPersonaDTO> dtos = persons.stream().map(personMapper::toDTO).toList();
        return ResponseEntity.ok(dtos);
    }
}
