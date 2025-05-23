package com.clinic.client_service.Controller;

import com.clinic.client_service.Service.PersonaService;
import com.clinic.client_service.domain.Dto.request.create.crearPersonaDto;
import com.clinic.client_service.domain.Dto.request.update.UpdatePersonaDTO;
import com.clinic.client_service.domain.Dto.response.GetPersonaDTO;
import com.clinic.client_service.domain.Genero;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/api/persons")
public class PersonaController {
    private final PersonaService personService;

    @GetMapping("/{id}")
    public ResponseEntity<GetPersonaDTO> getPersonById(@PathVariable Long id) {
        return personService.getPersonById(id);
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<GetPersonaDTO> getPersonByDni(@PathVariable String dni) {
        return personService.getPersonByDni(dni);
    }

    @GetMapping("/ids")
    public ResponseEntity<List<GetPersonaDTO>> getPersonsByIds(@RequestParam Set<Long> ids) {
        return personService.getPersonsByIds(ids);
    }

    @GetMapping
    public ResponseEntity<List<GetPersonaDTO>> getAllPersons(
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) String dni,
            @RequestParam(required = false) Genero gender) {

        return personService.getAllPersons(fullName, name, surname, dni, gender);
    }

    @GetMapping("/fullName")
    public ResponseEntity<Page<GetPersonaDTO>> getAllPersonsByFullName(
            @PageableDefault(size = 10, sort = "surname", direction =
                    Sort.Direction.ASC) Pageable pageable,
            @RequestParam(required = false) String fullName) {

        return personService.getPersonsByFullName(fullName, pageable);
    }

    @PostMapping
    public ResponseEntity<GetPersonaDTO> createPerson(@RequestBody crearPersonaDto createPersonDTO) {
        return personService.createPerson(createPersonDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetPersonaDTO> updatePerson(@PathVariable Long id,
                                                     @RequestBody UpdatePersonaDTO updatePersonDTO) {
        return personService.updatePerson(id, updatePersonDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        return personService.deletePerson(id);
    }
}
