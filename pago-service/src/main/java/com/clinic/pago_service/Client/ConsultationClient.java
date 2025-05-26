package com.clinic.pago_service.Client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "consultation-sv")
public interface ConsultationClient {
}
