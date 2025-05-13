package com.thekingmoss.application.dto.producto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductoResponseDto {
    private Long idProducto;
    private String nombreProducto;
    private Integer stock;
    private BigDecimal precioUnitario;
    private BigDecimal descuento;
    private String tamanio;
    private BigDecimal peso;
    private Long categoriaId;
    private String nombreCategoria;
}
