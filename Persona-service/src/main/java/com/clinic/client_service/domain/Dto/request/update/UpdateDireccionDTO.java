package com.clinic.client_service.domain.Dto.request.update;

import jakarta.validation.constraints.Size;

public record UpdateDireccionDTO(

        @Size(max = 100, message = "Street must not exceed 100 characters")
        String calle,

        @Size(max = 10, message = "Number must not exceed 10 characters")
        String numero,

        @Size(max = 5, message = "Floor must not exceed 5 characters")
        String piso,

        @Size(max = 5, message = "Apartment must not exceed 5 characters")
        String departamento,

        @Size(max = 50, message = "City must not exceed 50 characters")
        String ciudad,

        @Size(max = 50, message = "Province must not exceed 50 characters")
        String provincia,

        @Size(max = 10, message = "Postal code must not exceed 10 characters")
        String codigopostal
) {
}
