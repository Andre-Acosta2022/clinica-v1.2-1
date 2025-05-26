package com.clinic.pago_service.event.Model;


import lombok.Data;

@Data
public class Pacientes {
    private Long id;
    private String name;
    private String surname;
    private String dni;
    private String email;
    private String phone;
    private Address address;

}
