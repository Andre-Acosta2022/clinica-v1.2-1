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
@Table(name="pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="nombre")
    private String nombre;
    @Column(name="apellido")
    private String apellido;
    @Column(name="documentoIdentidad")
    private String documentoIdentidad;
    @Column(name="telefono")
    private String telefono;
    @Column(name="direccion")
    private String direccion;
    @Column(name="email")
    private String email;
    @Column(name="fechaRegistro")
    private String fechaRegistro;

}
