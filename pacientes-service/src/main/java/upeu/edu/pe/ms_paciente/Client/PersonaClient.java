package upeu.edu.pe.ms_paciente.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import upeu.edu.pe.ms_paciente.Client.FallBack.PersonaFallbackFactory;
import upeu.edu.pe.ms_paciente.domain.Dto.PersonaDto;
import upeu.edu.pe.ms_paciente.domain.model.Genero;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@FeignClient(name = "client-service", fallbackFactory = PersonaFallbackFactory.class)
public interface PersonaClient {

    @GetMapping("/api/persona/{id}")
    PersonaDto findById(@PathVariable Long id);

    @GetMapping("/api/persons/ids")
    List<PersonaDto> findAllById(@RequestParam Set<Long> ids);

    @GetMapping("/api/persons/dni/{dni}")
    Optional<PersonaDto> findByDni(@PathVariable String dni);

    @GetMapping("/api/persons")
    List<PersonaDto> getAllPersons(
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) String dni,
            @RequestParam(required = false) Genero gender);

    @PostMapping("/api/persons")
    PersonaDto createPerson(PersonaDto person);

    @PutMapping("/api/persons/{id}")
    PersonaDto updatePerson(@PathVariable Long id, @RequestBody PersonaDto person);

    @GetMapping("/api/persons/fullName")
    Page<PersonaDto> getAllPersonsByFullName(@PageableDefault(size = 10, sort = "surname", direction = Sort.Direction.ASC) Pageable pageable,
                                         @RequestParam(required = false) String fullName);

}
