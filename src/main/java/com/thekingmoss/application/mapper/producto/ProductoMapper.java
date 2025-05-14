package com.thekingmoss.application.mapper.producto;

import com.thekingmoss.application.dto.producto.ProductoRequestDto;
import com.thekingmoss.application.dto.producto.ProductoResponseDto;
import com.thekingmoss.domain.entity.Categoria;
import com.thekingmoss.domain.entity.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {
    public ProductoResponseDto toDto(Producto producto) {
        return ProductoResponseDto.builder()
                .idProducto(producto.getProductoId())
                .nombreProducto(producto.getNombre())
                .stock(producto.getStock())
                .precioUnitario(producto.getPrecioUnitario())
                .descuento(producto.getDescuento())
                .descripcion(producto.getDescripcion())
                .tamanio(producto.getTamanio())
                .peso(producto.getPeso())
                .categoriaId(producto.getCategoria().getCategoriaId())
                .nombreCategoria(producto.getCategoria().getNombreCategoria())
                .build();
    }

    public Producto toEntity(ProductoRequestDto requestDto, Categoria categoria) {
        return Producto.builder()
                .nombre(requestDto.getNombreProducto())
                .stock(requestDto.getStock())
                .precioUnitario(requestDto.getPrecioUnitario())
                .descuento(requestDto.getDescuento())
                .descripcion(requestDto.getDescripcion())
                .tamanio(requestDto.getTamanio())
                .peso(requestDto.getPeso())
                .categoria(categoria)
                .build();
    }

    public Producto toEntityProducto(ProductoResponseDto responseDto) {
        return Producto.builder()
                .productoId(responseDto.getIdProducto())
                .nombre(responseDto.getNombreProducto())
                .stock(responseDto.getStock())
                .precioUnitario(responseDto.getPrecioUnitario())
                .descuento(responseDto.getDescuento())
                .descripcion(responseDto.getDescripcion())
                .tamanio(responseDto.getTamanio())
                .peso(responseDto.getPeso())
                .build();
    }
}
