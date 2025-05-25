package upeu.edu.pe.ms_paciente.domain.Dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PacienteDto {
    private Long id;
    private Long personId;
    private SeguroMedicoDto seguroMedico;
    private Boolean deleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
