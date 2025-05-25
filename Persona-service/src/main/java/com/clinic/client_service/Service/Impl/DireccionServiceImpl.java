package com.clinic.client_service.Service.Impl;

import com.clinic.client_service.Service.DireccionService;
import com.clinic.client_service.domain.Direccion;
import com.clinic.client_service.domain.Dto.request.create.crearDireccionDto;
import com.clinic.client_service.domain.Dto.request.update.UpdateDireccionDTO;
import com.clinic.client_service.mapper.DireccionMapper;
import org.springframework.stereotype.Service;

@Service
public class DireccionServiceImpl implements DireccionService {

    private final DireccionMapper addressMapper;

    public DireccionServiceImpl(DireccionMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    @Override
    public Direccion createAddress(crearDireccionDto createAddressDTO) {
        return addressMapper.toEntity(createAddressDTO);
    }

    @Override
    public Direccion updateAddress(Direccion address, UpdateDireccionDTO addressDTO) {
        addressMapper.updateEntity(address, addressDTO);
        return address;
    }
}
