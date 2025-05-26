package com.clinic.pago_service.specification;

import com.clinic.pago_service.Domain.revenue.Revenue;
import com.clinic.pago_service.Domain.revenue.RevenueType;
import com.clinic.pago_service.event.Model.medical_services.ServiceType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class RevenueSpecification {

    public static Specification<Revenue> byDate(String date, String startDate, String endDate) {
        return (root, query, cb) ->
        {
            Predicate predicate = cb.conjunction();
            if (date != null) {
                return cb.equal(root.get("date"), LocalDate.parse(date));
            }
            if(startDate != null){
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("date"), LocalDate.parse(startDate)));
            }
            if(endDate != null){
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("date"), LocalDate.parse(endDate)));
            }
            return predicate;
        };
    }


    public static Specification<Revenue> bySpeciality(String speciality) {
        return (root, query, cb) -> speciality == null? null : cb.equal(root.get("specialityName"), speciality);
    }

    public static Specification<Revenue> byMedicalService(ServiceType medicalService) {
        return (root, query, cb) -> medicalService == null? null : cb.equal(root.get("medicalServiceType"), medicalService);
    }


    public static Specification<Revenue> byType(RevenueType type) {
        return (root, query, cb) -> type == null? null : cb.equal(root.get("revenueType"), type);
    }
}
