package upeu.edu.pe.ms_paciente.domain.Dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PacienteRequest {
    @NotNull(message = "El personId es obligatorio")
    private Long personId;

    private Long seguroMedicoId; // solo id para referenciar la aseguradora

    private Boolean deleted;
}
