package com.clinic.incident_service.service;



import com.clinic.incident_service.domain.Medico;

import java.util.List;

public interface MedicoService {
    Medico create(Medico m);
    Medico update(Medico m);
    void delete(Medico id);
    Medico read(Long id);
    List<Medico> readAll();

}
