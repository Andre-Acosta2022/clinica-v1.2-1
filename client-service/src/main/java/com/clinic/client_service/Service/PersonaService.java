package com.clinic.client_service.Service;
import com.clinic.client_service.domain.Dto.request.create.crearPersonaDto;
import com.clinic.client_service.domain.Dto.request.update.UpdatePersonaDTO;
import com.clinic.client_service.domain.Dto.response.GetPersonaDTO;
import com.clinic.client_service.domain.Genero;
import com.clinic.client_service.domain.persona;
import com.clinic.client_service.mapper.PersonaMapper;
import com.clinic.client_service.repository.PersonaRepository;
import com.clinic.client_service.specification.PersonaSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class PersonaService {

    private final PersonaRepository personRepository;
    private final PersonaMapper personMapper;
    private final DireccionService addressService;

    public ResponseEntity<GetPersonaDTO> getPersonById(Long id) {
        persona person = personRepository.findByIdAndNotDeleted(id).orElseThrow(() -> new EntityNotFoundException(
                "Person not found with id: " + id));
        return ResponseEntity.status(HttpStatus.OK).body(personMapper.toDTO(person));
    }

    public ResponseEntity<GetPersonaDTO> createPerson(crearPersonaDto createPersonDTO) {
        persona person = personMapper.toEntity(createPersonDTO);

        person.setDireccion(addressService.createAddress(createPersonDTO.getAddress()));

        person.setBirthdate(LocalDate.parse(createPersonDTO.getBirthdate()));
        personRepository.save(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(personMapper.toDTO(person));
    }

    public ResponseEntity<GetPersonaDTO> updatePerson(Long id, UpdatePersonaDTO updatePersonDTO) {
        persona person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Person not found with personId: " + id));
        personMapper.updateEntity(person, updatePersonDTO);

        if (updatePersonDTO.address() != null) {
            person.setDireccion(addressService.createAddress(updatePersonDTO.address()));
        }

        GetPersonaDTO personDTO = personMapper.toDTO(personRepository.save(person));
        return ResponseEntity.status(HttpStatus.OK).body(personDTO);
    }

    public ResponseEntity<Void> deletePerson(Long id) {
        persona person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Person not found with personId: " + id));
        person.setDeleted(true);
        personRepository.save(person);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    public ResponseEntity<GetPersonaDTO> getPersonByDni(String dni) {
        persona person = personRepository.findByDni(dni).orElseThrow(() -> new EntityNotFoundException("Person not found with dni: " + dni));
        return ResponseEntity.status(HttpStatus.OK).body(personMapper.toDTO(person));
    }

    public ResponseEntity<List<GetPersonaDTO>> getPersonsByIds(Set<Long> ids) {
        List<persona> persons = personRepository.findAllByIdAndNotDeleted(ids);
        List<GetPersonaDTO> personsDTO = persons.stream().map(personMapper::toDTO).toList();
        return ResponseEntity.status(HttpStatus.OK).body(personsDTO);
    }

    public ResponseEntity<Page<GetPersonaDTO>> getPersonsByFullName(String fullName, Pageable pageable) {
        Page<persona> persons = personRepository.findAllByFullNameAndNotDeleted(fullName, pageable);
        Page<GetPersonaDTO> personsDTO = persons.map(personMapper::toDTO);
        return ResponseEntity.status(HttpStatus.OK).body(personsDTO);
    }

    public ResponseEntity<List<GetPersonaDTO>> getAllPersons(String fullName, String name,
                                                            String surname, String dni,
                                                            Genero gender) {

        Specification<persona> specification = Specification.where(PersonaSpecification.deletedEqual(false))
                .and(PersonaSpecification.fullNameLike(fullName))
                .and(PersonaSpecification.nameLike(name))
                .and(PersonaSpecification.surnameLike(surname))
                .and(PersonaSpecification.dniLike(dni))
                .and(PersonaSpecification.genderEqual(gender));

        List<persona> persons = personRepository.findAll(specification);
        List<GetPersonaDTO> personsDTO = persons.stream().map(personMapper::toDTO).toList();
        return ResponseEntity.status(HttpStatus.OK).body(personsDTO);
    }
}
