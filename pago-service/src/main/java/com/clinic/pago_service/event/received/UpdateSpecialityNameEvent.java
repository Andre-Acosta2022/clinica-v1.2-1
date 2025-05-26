package com.clinic.pago_service.event.received;

import lombok.Data;

@Data
public class UpdateSpecialityNameEvent {
    private String newName;
    private String oldName;
}
