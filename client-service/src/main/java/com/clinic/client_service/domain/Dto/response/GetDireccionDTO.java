package com.clinic.client_service.domain.Dto.response;

public record GetDireccionDTO(
        String street,
        String number,
        String floor,
        String apartment,
        String city,
        String province,
        String postalCode
) {
}
