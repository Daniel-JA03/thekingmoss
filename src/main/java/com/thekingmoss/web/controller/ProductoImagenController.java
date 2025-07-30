package com.thekingmoss.web.controller;

import com.thekingmoss.application.dto.productoImagen.ProductoImagenResponse;
import com.thekingmoss.application.service.IProductoImagenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/productoImagen")
@RequiredArgsConstructor
public class ProductoImagenController {
    private final IProductoImagenService productoImagenService;

    @GetMapping
    public List<ProductoImagenResponse> listarProductosImagenes() {
        return productoImagenService.listarProductoImagen();
    }

    @PostMapping
    public ResponseEntity<ProductoImagenResponse> crearProductoImagen(
            @RequestParam("img") MultipartFile imagen,
            @RequestParam("productoId") Long productoId) throws IOException {

        ProductoImagenResponse response = productoImagenService.crearProductoImagen(imagen, productoId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ProductoImagenResponse buscarProductoImagenPorId(@PathVariable Long id) {
        return productoImagenService.buscarProductoImagenPorId(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProductoImagenResponse> actualizarProductoImagen(
            @PathVariable Long id,
            @RequestParam(value = "img", required = false) MultipartFile nuevaImagen,
            @RequestParam("productoId") Long nuevoProductoId) throws IOException {

        // Llama al servicio para actualizar la imagen
        ProductoImagenResponse response = productoImagenService.actualizarProductoImagen(id, nuevaImagen, nuevoProductoId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public void eliminarProductoImagen(@PathVariable Long id) {
        productoImagenService.eliminarProductoImagen(id);
    }
}
