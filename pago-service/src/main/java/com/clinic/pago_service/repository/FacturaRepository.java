package com.clinic.pago_service.repository;

import com.clinic.pago_service.Domain.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
    Optional<Factura> findByPatientId(Long patientId);

    @Query("SELECT i FROM Factura i WHERE i.payment.id = :paymentId")
    Optional<Factura> findByPaymentId(Long paymentId);
}
