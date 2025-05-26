package com.clinic.incident_service.serviceimpl;

import com.clinic.incident_service.service.GenericService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class GenericServiceImpl<T,ID> implements GenericService<T,ID> {
    protected abstract JpaRepository<T, ID> getRepository();
    @Override
    public T create(T entidad) {
        return getRepository().save(entidad);
    }

    @Override
    public T read(ID id) {
        return getRepository().findById(id).orElse(null);
    }

    @Override
    public T update(ID id,T entidad) {
        if (getRepository().existsById(id)) {
            return getRepository().save(entidad);
        }
        return null;
    }

    @Override
    public List<T> readAll() {
        return getRepository().findAll();
    }

    @Override
    public void delete(ID id) {
        getRepository().deleteById(id);
    }
}
