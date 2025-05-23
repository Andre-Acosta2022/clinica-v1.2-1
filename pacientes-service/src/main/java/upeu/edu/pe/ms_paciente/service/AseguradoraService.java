package upeu.edu.pe.ms_paciente.service;
import upeu.edu.pe.ms_paciente.domain.SeguroMedico;

import java.util.List;
import java.util.Optional;
public interface AseguradoraService {
    SeguroMedico create(SeguroMedico a);
    SeguroMedico update(SeguroMedico a);
    void delete(Long id);
    Optional<SeguroMedico> read(Long id);
    List<SeguroMedico> readAll();
}
