package com.clinic.client_service.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.time.LocalDate;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="personas")
@EntityListeners(AuditingEntityListener.class)
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personId;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String surname;

    @Column(nullable = false, length = 8, unique = true)
    @Pattern(regexp = "^[0-9]{7,8}$")
    private String dni;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false)
    @Email
    private String email;

    @Column(nullable = false, length = 25)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genero genero;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "direccion_id")
    private Direccion direccion;

    @Column(nullable = false)
    private boolean deleted = false;

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
