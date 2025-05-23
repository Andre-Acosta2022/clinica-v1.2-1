package upeu.edu.pe.ms_paciente.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upeu.edu.pe.ms_paciente.domain.SeguroMedico;
import upeu.edu.pe.ms_paciente.repository.AseguradoraRepository;
import upeu.edu.pe.ms_paciente.service.AseguradoraService;

import java.util.List;
import java.util.Optional;
@Service
public class AseguradoraServiceImpl implements AseguradoraService {

    @Autowired
    private AseguradoraRepository repository;

    @Override
    public SeguroMedico create(SeguroMedico a) {
        return repository.save(a);
    }

    @Override
    public SeguroMedico update(SeguroMedico a) {
        return repository.save(a);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<SeguroMedico> read(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<SeguroMedico> readAll() {
        return repository.findAll();
    }

}