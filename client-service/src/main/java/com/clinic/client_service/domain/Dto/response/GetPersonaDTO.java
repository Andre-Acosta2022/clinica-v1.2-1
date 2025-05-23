package com.clinic.client_service.domain.Dto.response;

import com.clinic.client_service.domain.Genero;

import java.time.LocalDate;

public record GetPersonaDTO(
        Long personId,
        String name,
        String surname,
        String dni,
        LocalDate birthdate,
        String email,
        String phone,
        Genero gender,
        GetDireccionDTO address,
        Boolean deleted
){
}
