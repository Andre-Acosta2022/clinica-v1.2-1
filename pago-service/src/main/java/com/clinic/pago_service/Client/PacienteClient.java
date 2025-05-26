package com.clinic.pago_service.Client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "patient-sv")
public interface PacienteClient {

}
