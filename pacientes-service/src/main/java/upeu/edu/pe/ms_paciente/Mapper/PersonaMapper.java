package upeu.edu.pe.ms_paciente.Mapper;

import org.mapstruct.Mapper;
import upeu.edu.pe.ms_paciente.domain.Dto.PersonaDto;

@Mapper(componentModel = "spring")
public interface PersonaMapper {
    PersonaDto toDTO(PersonaDto person);
}
