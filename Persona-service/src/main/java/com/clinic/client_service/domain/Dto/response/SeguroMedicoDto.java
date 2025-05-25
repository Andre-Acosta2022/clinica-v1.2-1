package com.clinic.client_service.domain.Dto.response;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class SeguroMedicoDto {
    private Long id;
    private String name;
    private Boolean deleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
