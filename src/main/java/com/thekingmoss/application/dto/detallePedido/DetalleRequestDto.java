package com.thekingmoss.application.dto.detallePedido;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DetalleRequestDto {
    private Long productoId;
    private Integer cantidad;
}
