package com.clinic.pago_service.specification;

import com.clinic.pago_service.Domain.payment.Payment;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PaymentSpecification {
    public static Specification<Payment> paymentByDate(String date, String fromDate, String toDate) {
        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            if (date != null) {
                predicate = cb.and(predicate, cb.equal(root.get("paymentDate"), LocalDate.parse(date)));
            }
            if (fromDate != null) {
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("paymentDate"), LocalDate.parse(fromDate)));
            }
            if (toDate != null) {
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("paymentDate"), LocalDate.parse(toDate)));
            }

            return predicate;
        };
    }

    public static Specification<Payment> paymentByAmount(BigDecimal amount, BigDecimal fromAmount, BigDecimal toAmount) {
        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            if (amount != null) {
                predicate = cb.and(predicate, cb.equal(root.get("amount"), amount));
            }
            if (fromAmount != null) {
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("amount"), fromAmount));
            }
            if (toAmount != null) {
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("amount"), toAmount));
            }
            return predicate;
        };
    }

    public static Specification<Payment> paymentByCanceled(Boolean canceled) {
        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            if (canceled != null) {
                predicate = cb.and(predicate, cb.equal(root.get("canceled"), canceled));
            }
            return predicate;
        };
    }

    public static Specification<Payment> paymentByPatientId(Long patientId) {
        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            if (patientId != null) {
                predicate = cb.and(predicate, cb.equal(root.get("patientId"), patientId));
            }
            return predicate;
        };
    }

    public static Specification<Payment> paymentByConsultationId(Long consultationId) {
        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            if (consultationId != null) {
                predicate = cb.and(predicate, cb.equal(root.get("consultationId"), consultationId));
            }
            return predicate;
        };
    }
}
