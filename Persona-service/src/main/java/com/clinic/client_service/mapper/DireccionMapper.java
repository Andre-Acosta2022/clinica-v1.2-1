package com.clinic.client_service.mapper;

import com.clinic.client_service.domain.Direccion;
import com.clinic.client_service.domain.Dto.request.create.crearDireccionDto;
import com.clinic.client_service.domain.Dto.request.update.UpdateDireccionDTO;
import com.clinic.client_service.domain.Dto.response.GetDireccionDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DireccionMapper {
    GetDireccionDTO toDTO(Direccion direccion);
    Direccion toEntity(crearDireccionDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget Direccion entity, UpdateDireccionDTO dto);
}