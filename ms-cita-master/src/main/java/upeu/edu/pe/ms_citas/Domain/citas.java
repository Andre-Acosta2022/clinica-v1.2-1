package upeu.edu.pe.ms_citas.Domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "Citas")
public class citas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "paciente_id")
    private Long pacienteId; // FK a MS-Pacientes

    @Column(name = "medico_id")
    private Long medicoId;   // FK a MS-Medicos

    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;

    @Column(name = "motivo")
    private String motivo;

    @Column(name = "estado")
    private String estado; // ej: pendiente, confirmada, cancelada
}
