package upeu.edu.pe.ms_paciente.service;
import upeu.edu.pe.ms_paciente.domain.aseguradora;

import java.util.List;
import java.util.Optional;
public interface AseguradoraService {
    aseguradora create(aseguradora a);
    aseguradora update(aseguradora a);
    void delete(Long id);
    Optional<aseguradora> read(Long id);
    List<aseguradora> readAll();
}
