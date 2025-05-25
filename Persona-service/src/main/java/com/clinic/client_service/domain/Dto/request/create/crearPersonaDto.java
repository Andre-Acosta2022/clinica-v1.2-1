package com.clinic.client_service.domain.Dto.request.create;

import com.clinic.client_service.domain.Genero;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class crearPersonaDto {

    @NotBlank(message = "Name cannot be empty")
    @Size(max = 50, message = "Name must not exceed 50 characters")
    private String name;

    @NotBlank(message = "Surname cannot be empty")
    @Size(max = 50, message = "Surname must not exceed 50 characters")
    private String surname;

    @NotBlank(message = "DNI cannot be empty")
    @Pattern(regexp = "^[0-9]{7,8}$", message = "DNI must contain 7 or 8 numeric digits")
    private String dni;

    @NotNull(message = "Birthdate cannot be null")
    @Past(message = "Birthdate must be a date in the past")
    private String birthdate;  // Puede ser LocalDate si configuras JSON parsing

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email must be a valid email address")
    private String email;

    @NotBlank(message = "Phone cannot be empty")
    @Size(max = 25, message = "Phone must not exceed 25 characters")
    private String phone;

    @NotNull(message = "Gender cannot be null")
    private Genero genero;

    @NotNull(message = "Address cannot be null")
    @Valid
    private crearDireccionDto direccion;
}

