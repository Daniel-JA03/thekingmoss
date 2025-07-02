package com.thekingmoss.application.mapper.productoImagen;

import com.thekingmoss.application.dto.productoImagen.ProductoImagenRequestDto;
import com.thekingmoss.application.dto.productoImagen.ProductoImagenResponse;
import com.thekingmoss.domain.entity.Producto;
import com.thekingmoss.domain.entity.ProductoImagen;
import org.springframework.stereotype.Component;

@Component
public class ProductoImagenMapper {
    public ProductoImagenResponse toDto(ProductoImagen productoImagen) {
        return ProductoImagenResponse.builder()
                .productoImagenId(productoImagen.getProductoImagenId())
                .imagenUrl(productoImagen.getImagenUrl())
                .productoId(productoImagen.getProducto().getProductoId())
                .nombreProducto(productoImagen.getProducto().getNombre())
                .build();
    }
    public ProductoImagen toEntity(ProductoImagenRequestDto requestDto, Producto producto) {
        return ProductoImagen.builder()
                .imagenUrl(requestDto.getImagenUrl())
                .producto(producto)
                .build();
    }
}
