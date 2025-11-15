package com.thekingmoss.application.service;

import com.thekingmoss.application.dto.carrito.CarritoRequestDto;
import com.thekingmoss.application.dto.carrito.CarritoResponseDto;

import java.util.List;

public interface ICarritoService {
    List<CarritoResponseDto> obtenerCarritoPorUsuario(Long usuarioId);
    CarritoResponseDto agregarProducto(Long usuarioId, CarritoRequestDto request);
    CarritoResponseDto actualizarCantidad(Long usuarioId, CarritoRequestDto request);
    void eliminarProducto(Long usuarioId, Long productoId);
    void vaciarCarrito(Long usuarioId);
}
