package com.clinic.client_service.mapper;

import com.clinic.client_service.domain.Dto.request.create.crearPersonaDto;
import com.clinic.client_service.domain.Dto.request.update.UpdatePersonaDTO;
import com.clinic.client_service.domain.Dto.response.GetPersonaDTO;
import com.clinic.client_service.domain.persona;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = DireccionMapper.class)
public interface PersonaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "birthdate", ignore = true)
    persona toEntity(crearPersonaDto personDTO);

    @Mapping(target = "personId", source = "id")
    GetPersonaDTO toDTO(persona person);

    @Mapping(target = "address", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget persona person, UpdatePersonaDTO personDTO);
}
