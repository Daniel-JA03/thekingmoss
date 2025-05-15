package com.thekingmoss.application.service;

import com.thekingmoss.application.dto.productoImagen.ProductoImagenRequestDto;
import com.thekingmoss.application.dto.productoImagen.ProductoImagenResponse;
import com.thekingmoss.domain.entity.ProductoImagen;

import java.util.List;

public interface IProductoImagenService {
    List<ProductoImagenResponse> listarProductoImagen();
    ProductoImagenResponse crearProductoImagen(ProductoImagenRequestDto requestDto);
    ProductoImagenResponse buscarProductoImagenPorId(Long id);
    void eliminarProductoImagen(Long id);
}
