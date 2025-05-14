package com.thekingmoss.application.dto.detallePedido;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DetallePedidoRequestDto {
    private Long productoId;
    private Integer cantidad;
}
