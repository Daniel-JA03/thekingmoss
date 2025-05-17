package com.thekingmoss.web.controller;

import com.thekingmoss.application.dto.producto.ProductoRequestDto;
import com.thekingmoss.application.dto.producto.ProductoResponseDto;
import com.thekingmoss.application.service.IProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/producto")
@RequiredArgsConstructor
public class ProductoController {
    private final IProductoService productoService;

    @GetMapping
    public List<ProductoResponseDto> listarProductos() {
        return productoService.listarProductos();
    }

    @PostMapping
    public ProductoResponseDto crearProducto(@RequestBody ProductoRequestDto productoRequestDto) {
        return productoService.crearProducto(productoRequestDto);
    }

    @GetMapping("{id}")
    public ProductoResponseDto buscarProductoPorId(@PathVariable Long id) {
        return productoService.buscarProductoPorId(id);
    }

    @GetMapping("/categoria/{nombreCategoria}")
    public List<ProductoResponseDto> listarProductoPorNombreCategoria(@PathVariable String nombreCategoria) {
        return productoService.listarProductoPorNombreCategoria(nombreCategoria);
    }

    @PutMapping("/{id}")
    public ProductoResponseDto actualizarProducto(@PathVariable Long id ,@RequestBody ProductoRequestDto productoRequestDto) {
        return productoService.actualizarProducto(id, productoRequestDto);
    }

    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
    }
}
