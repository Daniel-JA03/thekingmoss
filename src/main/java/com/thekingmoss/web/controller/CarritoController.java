package com.thekingmoss.web.controller;

import com.thekingmoss.application.dto.carrito.CarritoRequestDto;
import com.thekingmoss.application.dto.carrito.CarritoResponseDto;
import com.thekingmoss.application.service.ICarritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carrito")
@RequiredArgsConstructor
public class CarritoController {
    private final ICarritoService carritoService;

    @GetMapping("/usuario/{usuarioId}")
    public List<CarritoResponseDto> obtenerCarritoPorUsuario(@PathVariable Long usuarioId) {
        return carritoService.obtenerCarritoPorUsuario(usuarioId);
    }

    @PostMapping("/usuario/{usuarioId}/agregar")
    public CarritoResponseDto agregarProducto(
            @PathVariable Long usuarioId,
            @RequestBody CarritoRequestDto request) {
        return carritoService.agregarProducto(usuarioId, request);
    }

    @PutMapping("/usuario/{usuarioId}/actualizar")
    public CarritoResponseDto actualizarCantidad(
            @PathVariable Long usuarioId,
            @RequestBody CarritoRequestDto request) {
        return carritoService.actualizarCantidad(usuarioId, request);
    }

    // Eliminar un producto del carrito de un usuario
    @DeleteMapping("/usuario/{usuarioId}/producto/{productoId}")
    public void eliminarProducto(
            @PathVariable Long usuarioId,
            @PathVariable Long productoId) {
        carritoService.eliminarProducto(usuarioId, productoId);
    }

    // Vaciar el carrito de un usuario
    @DeleteMapping("/usuario/{usuarioId}/vaciar")
    public void vaciarCarrito(@PathVariable Long usuarioId) {
        carritoService.vaciarCarrito(usuarioId);
    }
}
