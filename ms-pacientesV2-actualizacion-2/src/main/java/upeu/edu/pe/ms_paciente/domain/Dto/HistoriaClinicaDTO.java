package upeu.edu.pe.ms_paciente.domain.Dto;
import lombok.Data;
import java.time.LocalDate;
@Data
public class HistoriaClinicaDTO {
    private Long id;
    private Long pacienteId;
    private String descripcion;
    private LocalDate fechaConsulta;
    private String medicoNombre;
}
