package upeu.edu.pe.ms_paciente.domain.Dto;

import lombok.Data;
import upeu.edu.pe.ms_paciente.domain.model.Genero;

@Data
public class PersonaDto {
    private Long personId;
    private String name;
    private String surname;
    private String dni;
    private String email;
    private String phone;
    private String birthdate;
    private String address;
    private Genero gender;
}
