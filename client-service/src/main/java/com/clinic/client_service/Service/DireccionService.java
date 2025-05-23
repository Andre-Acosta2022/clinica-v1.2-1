package com.clinic.client_service.Service;

import com.clinic.client_service.domain.Direccion;
import com.clinic.client_service.domain.Dto.request.create.crearDireccionDto;
import com.clinic.client_service.domain.Dto.request.update.UpdateDireccionDTO;
import com.clinic.client_service.mapper.DireccionMapper;
import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DireccionService {
    private final DireccionMapper addressMapper;

    public Direccion createAddress(crearDireccionDto createAddressDTO) {
        return  addressMapper.toEntity(createAddressDTO);
    }

    public Direccion updateAddress(Direccion address, UpdateDireccionDTO addressDTO) {
        addressMapper.updateEntity(address, addressDTO);
        return address;
    }
}
