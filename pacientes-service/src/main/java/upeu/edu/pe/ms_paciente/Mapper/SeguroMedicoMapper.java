package upeu.edu.pe.ms_paciente.Mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import upeu.edu.pe.ms_paciente.domain.Dto.SeguroMedicoDto;
import upeu.edu.pe.ms_paciente.domain.Dto.request.SeguroMedicoRequest;
import upeu.edu.pe.ms_paciente.domain.SeguroMedico;

@Mapper(componentModel = "spring")
public interface SeguroMedicoMapper {

    SeguroMedicoDto toDTO(SeguroMedico entity);

    SeguroMedico toEntity(SeguroMedicoRequest dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(SeguroMedicoRequest dto, @MappingTarget SeguroMedico entity);
}
