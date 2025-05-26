package com.clinic.pago_service.repository;

import com.clinic.pago_service.event.Model.medical_services.MedicalPackage;
import com.clinic.pago_service.event.Model.medical_services.MedicalServicesWrapped;
import com.clinic.pago_service.event.Model.medical_services.ServiceType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@FeignClient(name = "medical-service-sv")
public interface ServicioMedicoRepository {
    @GetMapping("/api/medical-services/{id}")
    MedicalServicesWrapped getMedicalServiceById(@PathVariable Long id);

    @GetMapping("/api/medical-services/ids")
    List<MedicalServicesWrapped> findAllById(@RequestParam Set<Long> ids);

    @GetMapping("/api/medical-services/public/types")
    Set<ServiceType> getAllServicesType();

    @GetMapping("/api/packages/{id}")
    MedicalPackage getPackageById(@PathVariable Long id);
}
