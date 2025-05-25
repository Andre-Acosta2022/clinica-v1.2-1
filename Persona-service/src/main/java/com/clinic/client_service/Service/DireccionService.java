package com.clinic.client_service.Service;

import com.clinic.client_service.domain.Direccion;
import com.clinic.client_service.domain.Dto.request.create.crearDireccionDto;
import com.clinic.client_service.domain.Dto.request.update.UpdateDireccionDTO;


public interface DireccionService {

    Direccion createAddress(crearDireccionDto createAddressDTO);
    Direccion updateAddress(Direccion address, UpdateDireccionDTO addressDTO);
}

