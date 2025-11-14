package com.thekingmoss.application.dto.carrito;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoCarritoDto {
    private Long productoId;
    private String nombreProducto;
    private String imagenUrl;
    private BigDecimal precioUnitario;
    private Integer stock;
}
