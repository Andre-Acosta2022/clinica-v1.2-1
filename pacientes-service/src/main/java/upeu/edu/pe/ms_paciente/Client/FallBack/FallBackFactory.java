package upeu.edu.pe.ms_paciente.Client.FallBack;

import feign.FeignException;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import upeu.edu.pe.ms_paciente.Client.PersonaClient;
import upeu.edu.pe.ms_paciente.domain.Dto.PersonaDto;
import upeu.edu.pe.ms_paciente.domain.model.Genero;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class FallBackFactory implements FallbackFactory<PersonaClient> {

    @Override
    public PersonaClient create(Throwable cause) {
        return new PersonaClient() {
            @Override
            public PersonaDto findById(Long id) {
                return null;
            }

            @Override
            public List<PersonaDto> findAllById(Set<Long> ids) {
                return List.of();
            }

            @Override
            public Optional<PersonaDto> findByDni(String dni) {
                if (cause instanceof FeignException.NotFound) {
                    return Optional.empty();
                }
                if (cause instanceof RuntimeException) {
                    throw (RuntimeException) cause;
                }
                throw new RuntimeException("Unhandled exception in Feign client", cause);
            }

            @Override
            public List<PersonaDto> getAllPersons(String fullName, String name, String surname, String dni, Genero gender) {
                return List.of();
            }

            @Override
            public Page<PersonaDto> getAllPersonsByFullName(Pageable pageable, String fullName) {
                return new PageImpl<>(new ArrayList<>());
            }

            @Override
            public PersonaDto createPerson(PersonaDto person) {
                return null;
            }

            @Override
            public PersonaDto updatePerson(Long id, PersonaDto person) {
                return null;
            }
        };
    }


}
