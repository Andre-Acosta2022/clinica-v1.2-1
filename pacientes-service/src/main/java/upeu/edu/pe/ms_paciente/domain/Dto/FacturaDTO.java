package upeu.edu.pe.ms_paciente.domain.Dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FacturaDTO {
    private Long id;
    private Long pacienteId;
    private Double monto;
    private LocalDate fechaEmision;
    private String estado;
}