package upeu.edu.pe.ms_citas.Domain.Dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CitaDetalleDTO {
    private Long id;
    private LocalDateTime fechaHora;
    private String motivo;
    private String estado;

    // Datos relacionados traídos por Feign
    private PacienteDTO paciente;
    private MedicoDTO medico;
}
