package com.clinic.client_service.domain.Dto.request.create;



public record crearDireccionDto (
        String calle,
        String numero,
        String piso,
        String departamento,
        String ciudad,
        String provincia,
        String codigopostal
) {
}