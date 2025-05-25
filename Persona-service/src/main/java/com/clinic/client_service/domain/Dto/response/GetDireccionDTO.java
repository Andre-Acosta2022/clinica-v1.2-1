package com.clinic.client_service.domain.Dto.response;

public record GetDireccionDTO(
        Long direccionid,
        String calle,
        String numero,
        String piso,
        String departamento,
        String ciudad,
        String provincia,
        String codigopostal
) {
}
