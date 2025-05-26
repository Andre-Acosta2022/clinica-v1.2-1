package com.clinic.incident_service.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fecha")
    private String fecha;

    @Column(name = "lugar")
    private String lugar;

    @Column(name = "estado")
    private String estado;

    @ManyToOne
    @JoinColumn(name ="medico_id", nullable = false)
    private Medico medico;
}
