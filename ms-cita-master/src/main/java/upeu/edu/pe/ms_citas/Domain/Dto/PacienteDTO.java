package upeu.edu.pe.ms_citas.Domain.Dto;

import lombok.Data;

@Data
public class PacienteDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String documentoIdentidad;
    private String telefono;
    private String direccion;
    private String email;
    private String fechaRegistro;
}
