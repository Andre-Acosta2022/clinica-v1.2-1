package com.clinic.client_service.Service;
import com.clinic.client_service.domain.Dto.request.create.crearPersonaDto;
import com.clinic.client_service.domain.Dto.request.update.UpdatePersonaDTO;
import com.clinic.client_service.domain.Dto.response.GetPersonaDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Set;


public interface PersonaService {
    ResponseEntity<GetPersonaDTO> getPersonById(Long id);

    ResponseEntity<GetPersonaDTO> createPerson(crearPersonaDto dto);

    ResponseEntity<GetPersonaDTO> updatePerson(Long id, UpdatePersonaDTO dto);

    ResponseEntity<Void> deletePerson(Long id);

    ResponseEntity<GetPersonaDTO> getPersonByDni(String dni);

    ResponseEntity<List<GetPersonaDTO>> getPersonsByIds(Set<Long> ids);

    ResponseEntity<Page<GetPersonaDTO>> getPersonsByFullName(String fullName, org.springframework.data.domain.Pageable pageable);

    ResponseEntity<List<GetPersonaDTO>> getAllPersons(String fullName, String name, String surname, String dni, com.clinic.client_service.domain.Genero gender);
}

