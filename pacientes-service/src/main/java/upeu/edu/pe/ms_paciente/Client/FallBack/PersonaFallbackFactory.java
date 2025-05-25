package upeu.edu.pe.ms_paciente.Client.FallBack;
import feign.FeignException;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import upeu.edu.pe.ms_paciente.Client.PersonaClient;
import upeu.edu.pe.ms_paciente.domain.Dto.PersonaDto;
import upeu.edu.pe.ms_paciente.domain.model.Genero;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class PersonaFallbackFactory implements FallbackFactory<PersonaClient>{

    @Override
    public PersonaClient create(Throwable cause) {
        return new PersonaClient() {

            @Override
            public PersonaDto findById(Long id) {
                // Puedes devolver null o un objeto vacío según convenga
                return null;
            }

            @Override
            public List<PersonaDto> findAllById(Set<Long> ids) {
                return Collections.emptyList();
            }

            @Override
            public Optional<PersonaDto> findByDni(String dni) {
                return Optional.empty();
            }

            @Override
            public List<PersonaDto> getAllPersons(String fullName, String name, String surname, String dni, Genero gender) {
                return Collections.emptyList();
            }

            @Override
            public PersonaDto createPerson(PersonaDto person) {
                return null;
            }

            @Override
            public PersonaDto updatePerson(Long id, PersonaDto person) {
                return null;
            }

            @Override
            public Page<PersonaDto> getAllPersonsByFullName(Pageable pageable, String fullName) {
                // Retorna página vacía para evitar errores
                return new PageImpl<>(Collections.emptyList());
            }
        };
    }

}
