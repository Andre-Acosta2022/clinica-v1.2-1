package com.clinic.client_service.Service.Impl;

import com.clinic.client_service.Service.DireccionService;
import com.clinic.client_service.domain.Direccion;
import com.clinic.client_service.domain.Dto.request.create.crearDireccionDto;
import com.clinic.client_service.domain.Dto.request.update.UpdateDireccionDTO;
import com.clinic.client_service.mapper.DireccionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DireccionServiceImpl implements DireccionService {
@Autowired
@Qualifier("direccionMapper")
    private final DireccionMapper addressMapper;

    public DireccionServiceImpl(DireccionMapper direccionMapper) {
        this.addressMapper = direccionMapper;
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
