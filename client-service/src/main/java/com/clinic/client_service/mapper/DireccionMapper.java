package com.clinic.client_service.mapper;

import com.clinic.client_service.domain.Direccion;
import com.clinic.client_service.domain.Dto.request.create.crearDireccionDto;
import com.clinic.client_service.domain.Dto.request.update.UpdateDireccionDTO;
import com.clinic.client_service.domain.Dto.response.GetDireccionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DireccionMapper {
    Direccion toEntity(crearDireccionDto createAddressDTO);

    void updateEntity(@MappingTarget Direccion address, UpdateDireccionDTO updateAddressDTO);

    GetDireccionDTO toDTO(Direccion address);
}
