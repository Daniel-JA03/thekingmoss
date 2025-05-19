package com.thekingmoss.application.service;

import com.thekingmoss.application.dto.productoImagen.ProductoImagenRequestDto;
import com.thekingmoss.application.dto.productoImagen.ProductoImagenResponse;
import com.thekingmoss.domain.entity.ProductoImagen;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IProductoImagenService {
    List<ProductoImagenResponse> listarProductoImagen();
    ProductoImagenResponse crearProductoImagen(MultipartFile imagen, Long productoId) throws IOException;
    ProductoImagenResponse buscarProductoImagenPorId(Long id);
    ProductoImagenResponse actualizarProductoImagen(Long id, MultipartFile nuevaImagen, Long nuevoProductoId) throws IOException;
    void eliminarProductoImagen(Long id);
}
