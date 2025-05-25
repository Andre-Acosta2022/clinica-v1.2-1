package com.clinic.client_service.mapper;

import com.clinic.client_service.domain.Dto.request.create.crearPersonaDto;
import com.clinic.client_service.domain.Dto.request.update.UpdatePersonaDTO;
import com.clinic.client_service.domain.Dto.response.GetPersonaDTO;
import com.clinic.client_service.domain.Persona;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = DireccionMapper.class)
public interface PersonaMapper {
    @Mapping(source = "direccion", target = "direccion")
    GetPersonaDTO toDTO(Persona persona);

    @Mapping(source = "direccion", target = "direccion")
    Persona toEntity(crearPersonaDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "direccion", target = "direccion")
    void updateEntity(@MappingTarget Persona entity, UpdatePersonaDTO dto);
}