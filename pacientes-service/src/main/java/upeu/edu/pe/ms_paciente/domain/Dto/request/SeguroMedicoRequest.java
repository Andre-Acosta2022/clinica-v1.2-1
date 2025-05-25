package upeu.edu.pe.ms_paciente.domain.Dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SeguroMedicoRequest {
    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    private Boolean deleted;
}
