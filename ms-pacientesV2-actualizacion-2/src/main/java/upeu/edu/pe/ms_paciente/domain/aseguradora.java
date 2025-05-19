package upeu.edu.pe.ms_paciente.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="aseguradora")
public class aseguradora {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="paciente_id", referencedColumnName="id")
    private Paciente paciente;

    @Column(name="nombreAseguradora")
    private String nombreAseguradora;

    @Column(name="numeroPoliza")
    private String numeroPoliza;
}
