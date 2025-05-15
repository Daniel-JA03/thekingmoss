package com.thekingmoss.application.service;

import com.thekingmoss.application.dto.producto.ProductoRequestDto;
import com.thekingmoss.application.dto.producto.ProductoResponseDto;

import java.util.List;

public interface IProductoService {
    List<ProductoResponseDto> listarProductos();
    ProductoResponseDto buscarProductoPorId(Long id);
    List<ProductoResponseDto> listarProductoPorNombreCategoria(String nombreCategoria);
    ProductoResponseDto crearProducto(ProductoRequestDto requestDto);
    ProductoResponseDto actualizarProducto(Long id,ProductoRequestDto requestDto);
    void eliminarProducto(Long id);
}
