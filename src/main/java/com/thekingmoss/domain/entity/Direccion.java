package com.thekingmoss.domain.entity;

import com.thekingmoss.domain.entity.types.TipoDireccion;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "direcciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "direcion_id")
    private Long direccionId;

    @Column(name = "pais", nullable = false)
    private String pais;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "provincia", nullable = false)
    private String provincia;

    @Column(name = "distrito", nullable = false)
    private String distrito;

    @Column(name = "dirección_referencia", nullable = false)
    private String referencia;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_direccion", nullable = false)
    private TipoDireccion tipoDireccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}
