package upeu.edu.pe.ms_paciente.service;

import java.util.List;

public interface BaseService<T, ID> {
    T findById(ID id);
    List<T> findAll();
    T save(T entity);
    T update(ID id, T entity);
    void delete(ID id);
}
