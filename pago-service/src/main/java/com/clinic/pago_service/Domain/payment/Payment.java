package com.clinic.pago_service.Domain.payment;

import com.clinic.pago_service.Domain.Factura;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@Data
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "consultation_id", nullable = false, updatable = false, unique = true)
    private Long consultationId;

    @Column(nullable = false, updatable = false)
    private BigDecimal amount;

    @Column(name = "payment_date", nullable = false, updatable = false)
    private LocalDateTime paymentDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false, updatable = false)
    private PaymentMethod paymentMethod;

    @OneToOne
    private Factura factura;

    private Boolean canceled;

    public Payment() {
        this.canceled = false;
        this.paymentDate = LocalDateTime.now();
    }
}
