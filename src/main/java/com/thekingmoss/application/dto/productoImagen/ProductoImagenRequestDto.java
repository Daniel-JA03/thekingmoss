package com.thekingmoss.application.dto.productoImagen;

import lombok.Data;

@Data
public class ProductoImagenRequestDto {
    private String imagenUrl;
    private Long productoId;
}
