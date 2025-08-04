package com.thekingmoss.domain.entity;

import com.thekingmoss.domain.entity.types.EstadoMensaje;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "contacto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactoid;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String email;

    private String asunto;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String mensaje;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoMensaje estado = EstadoMensaje.NUEVO;

    // Fecha en que se creó el registro. No debe modificarse una vez creado.
    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    /**
     * Asigna la fecha de creación automáticamente antes de guardar por primera vez.
     * Es útil cuando se usan builders o mappers que podrían omitir este campo.
     */
    @PrePersist
    public void asignarFechaCreacion() {
        if (this.fechaCreacion == null) {
            this.fechaCreacion = LocalDateTime.now();
        }
    }
}
