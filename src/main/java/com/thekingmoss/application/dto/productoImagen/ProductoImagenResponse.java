package com.thekingmoss.application.dto.productoImagen;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductoImagenResponse {
    private Long productoImagenId;
    private String imagenUrl;
    private Long productoId;
    private String nombreProducto;
}
