package com.clinic.incident_service.service;

import java.util.List;

public interface GenericService <T,ID>{

        T read(ID id);
        T create(T entidad);
        T update(ID id, T entidad);
        void delete(ID id);
        List<T> readAll();
}
