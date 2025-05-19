package com.thekingmoss.application.dto.productoImagen;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductoImagenRequestDto {
    private String imagenUrl;
    private Long productoId;
}
