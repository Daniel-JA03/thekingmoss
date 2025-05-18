package com.thekingmoss.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detalle_pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DetallePedido {
    @EmbeddedId
    @EqualsAndHashCode.Include
    private DetallePedidoId detallePedidoId;

    @MapsId("pedidoId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @MapsId("productoId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Column(nullable = false)
    private Integer cantidad;

    public DetallePedido(Pedido pedido, Producto producto, Integer cantidad) {
        this.pedido = pedido;
        this.producto = producto;
        this.cantidad = cantidad;
        this.detallePedidoId = new DetallePedidoId(pedido.getPedidoId(), producto.getProductoId());
    }
}
