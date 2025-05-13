package com.thekingmoss.application.dto.detallePedido;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DetallePedidoResponseDto {
    private Long productoId;
    private String nombreProducto;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal descuento;

    public BigDecimal getLineaTotal() {
        return precioUnitario.multiply(BigDecimal.valueOf(cantidad));
    }
}
