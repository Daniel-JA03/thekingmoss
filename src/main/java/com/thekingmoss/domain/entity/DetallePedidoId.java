package com.thekingmoss.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode
public class DetallePedidoId implements Serializable {
    @Column(name = "pedido_id")
    private Long pedidoId;

    @Column(name = "producto_id")
    private Long productoId;
}
