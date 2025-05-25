package upeu.edu.pe.ms_paciente.Mapper;

import org.mapstruct.*;
import upeu.edu.pe.ms_paciente.domain.Dto.PacienteDto;
import upeu.edu.pe.ms_paciente.domain.Dto.PersonaDto;
import upeu.edu.pe.ms_paciente.domain.Dto.SeguroMedicoDto;
import upeu.edu.pe.ms_paciente.domain.Dto.request.PacienteRequest;
import upeu.edu.pe.ms_paciente.domain.Paciente;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SeguroMedicoMapper.class})
public interface PacienteMapper {
    // Convierte entidad a DTO para respuesta
    @Mapping(source = "seguroMedico", target = "seguroMedico")
    PacienteDto toDTO(Paciente paciente);

    // Convierte DTO a entidad para crear o actualizar
    @Mapping(source = "seguroMedicoId", target = "seguroMedico.id")
    Paciente toEntity(PacienteRequest dto);

    // Actualiza entidad con valores del DTO request, ignorando nulos
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "seguroMedicoId", target = "seguroMedico.id")
    void updateEntityFromDto(PacienteRequest dto, @MappingTarget Paciente entity);
}
