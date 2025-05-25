package upeu.edu.pe.ms_paciente.domain.Dto.specification;

import jakarta.validation.constraints.Size;

public class SeguroMedicoSpecificationDTO {
    @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters")
    private String name;
}
