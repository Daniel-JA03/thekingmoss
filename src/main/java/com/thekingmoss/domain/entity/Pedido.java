package com.thekingmoss.domain.entity;

import com.thekingmoss.domain.entity.types.TipoEstadoPedido;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // Solo usa campos explícitos
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedido_id")
    @EqualsAndHashCode.Include // Incluye solo el ID en hashCode/equals
    private Long pedidoId;

    @Column(name = "fecha_pedido", nullable = false)
    private Date fechaPedido;

    @Column(name = "tipo_entrega", nullable = false)
    private String tipoEntrega;

    @Column(name = "informacion_pedido", nullable = false)
    private String informacionPedido;

    @Column(name = "instruccion_entrega", nullable = false)
    private String instruccionEntrega;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_pedido", nullable = false)
    private TipoEstadoPedido tipoEstadoPedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.REMOVE)
    @Builder.Default
    private Set<DetallePedido> detallePedidos  = new LinkedHashSet<>();
}
