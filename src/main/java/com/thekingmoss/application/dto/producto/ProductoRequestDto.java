package com.thekingmoss.application.dto.producto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductoRequestDto {
    private String nombreProducto;
    private Integer stock;
    private BigDecimal precioUnitario;
    private BigDecimal descuento;
    private String tamanio;
    private BigDecimal peso;
    private Long categoriaId;
}
