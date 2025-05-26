package com.clinic.pago_service.repository;

import com.clinic.pago_service.event.Model.Consultation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "consultation-sv")
public interface ConsultationRepository {

    @GetMapping("/api/consultations/{id}")
    Consultation getConsultation(@PathVariable Long id);

    @GetMapping("/api/consultations/ids")
    List<Consultation> findAllById(@RequestParam List<Long> consultationIds);
}
