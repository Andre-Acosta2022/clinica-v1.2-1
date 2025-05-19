package upeu.edu.pe.ms_citas.Domain.Dto;


import lombok.Data;

@Data
public class MedicoDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String especialidad;
    private String telefono;
    private String email;
}