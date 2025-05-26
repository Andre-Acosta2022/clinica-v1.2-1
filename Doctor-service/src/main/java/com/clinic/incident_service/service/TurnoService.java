package com.clinic.incident_service.service;



import com.clinic.incident_service.domain.Turno;

import java.util.List;

public interface TurnoService {
    Turno create(Turno m);
    Turno update(Turno m);
    void delete(Turno id);
    Turno read(Long id);
    List<Turno> readAll();
}
