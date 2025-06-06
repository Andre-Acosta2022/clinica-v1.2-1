package com.clinic.pago_service.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@FeignClient(name = "doctor-sv")
public interface SpecialityRepository {

    @GetMapping("/api/specialities/public/names")
    Set<String> getAllSpecialitiesName();
}
