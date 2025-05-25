package com.clinic.client_service.Client.FallBack;

import com.clinic.client_service.Client.PacienteClient;
import com.clinic.client_service.domain.Dto.response.PacienteDto;
import org.springframework.cloud.openfeign.FallbackFactory;
import java.util.Collections;
import java.util.List;

public class PacienteClientFallbackFactory implements FallbackFactory<PacienteClient> {

    @Override
    public PacienteClient create(Throwable cause) {
        return new PacienteClient() {

            @Override
            public PacienteDto getPacienteById(Long id) {
                // Retorno por defecto o null
                return null;
            }

            @Override
            public List<PacienteDto> getAllPacientes() {
                return Collections.emptyList();
            }
        };
    }
}