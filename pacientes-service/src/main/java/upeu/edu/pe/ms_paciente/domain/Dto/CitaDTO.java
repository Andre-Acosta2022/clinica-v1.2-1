package upeu.edu.pe.ms_paciente.domain.Dto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CitaDTO {
    private Long id;
    private Long pacienteId;
    private Long medicoId;
    private LocalDateTime fechaHora;
    private String motivo;
}
