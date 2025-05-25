package com.clinic.client_service.domain.Dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PacienteDto {
    private Long id;

    private Long personId;

    // Solo ID o detalles mínimos de SeguroMedico para no exponer toda la entidad
    private SeguroMedicoDto seguroMedico;

    private Boolean deleted;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
