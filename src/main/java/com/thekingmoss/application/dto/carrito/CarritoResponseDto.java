package com.thekingmoss.application.dto.carrito;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarritoResponseDto {
    private Long idCarrito;
    private Long usuarioId;
    private ProductoCarritoDto producto;
    private Integer cantidad;
}
