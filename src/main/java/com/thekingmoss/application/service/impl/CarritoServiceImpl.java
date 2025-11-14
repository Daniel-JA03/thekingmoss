package com.thekingmoss.application.service.impl;

import com.thekingmoss.application.dto.carrito.CarritoRequestDto;
import com.thekingmoss.application.dto.carrito.CarritoResponseDto;
import com.thekingmoss.application.mapper.carrito.CarritoMapper;
import com.thekingmoss.application.service.ICarritoService;
import com.thekingmoss.domain.entity.Carrito;
import com.thekingmoss.domain.entity.Producto;
import com.thekingmoss.domain.entity.Usuario;
import com.thekingmoss.domain.repository.ICarritoRepository;
import com.thekingmoss.domain.repository.IProductoRepository;
import com.thekingmoss.domain.repository.IUsuarioRepository;
import com.thekingmoss.web.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CarritoServiceImpl implements ICarritoService {
    private final ICarritoRepository carritoRepository;
    private final IUsuarioRepository usuarioRepository;
    private final IProductoRepository productoRepository;
    private final CarritoMapper carritoMapper;

    @Override
    @Transactional(readOnly = true)
    public List<CarritoResponseDto> obtenerCarritoPorUsuario(Long usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new ResourceNotFoundException("Usuario no encontrado con ID: " + usuarioId);
        }
        return carritoMapper.toDtoList(carritoRepository.findByUsuarioId(usuarioId));
    }

    @Override
    public CarritoResponseDto agregarProducto(Long usuarioId, CarritoRequestDto request) {
        // validar usuario
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + usuarioId));

        // validar producto y stock
        Producto producto = validarProductoYStock(request.getProductoId(), request.getCantidad());

        // verificar si existe en el carrito
        return carritoRepository.findByUsuarioIdAndProductoProductoId(usuarioId, request.getProductoId())
                .map(carritoExistente -> {
                    int nuevaCantidad = carritoExistente.getCantidad() + request.getCantidad();
                    if (nuevaCantidad > producto.getStock()) {
                        throw new IllegalStateException("No hay suficiente stock para este producto");
                    }
                    carritoExistente.setCantidad(nuevaCantidad);
                    return carritoMapper.toDto(carritoRepository.save(carritoExistente));
                })
                .orElseGet(() -> {
                    Carrito nuevoCarrito = carritoMapper.toEntity(usuario, producto, request);
                    return carritoMapper.toDto(carritoRepository.save(nuevoCarrito));
                });
    }

    @Override
    public CarritoResponseDto actualizarCantidad(Long usuarioId, CarritoRequestDto request) {
        // validar usuario
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new ResourceNotFoundException("Usuario no encontrado con ID: " + usuarioId);
        }

        // validar producto y cantidad
        Producto producto = validarProductoYStock(request.getProductoId(), request.getCantidad());

        // buscar item en carrito
        Carrito carrito = carritoRepository.findByUsuarioIdAndProductoProductoId(usuarioId, request.getProductoId())
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado en el carrito del usuario"));

        carrito.setCantidad(request.getCantidad());
        return carritoMapper.toDto(carritoRepository.save(carrito));
    }

    @Override
    public void eliminarProducto(Long usuarioId, Long productoId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new ResourceNotFoundException("Usuario no encontrado con ID: " + usuarioId);
        }
        Carrito carrito = carritoRepository.findByUsuarioIdAndProductoProductoId(usuarioId, productoId)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado en el carrito"));
        carritoRepository.delete(carrito);
    }

    @Override
    public void vaciarCarrito(Long usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new ResourceNotFoundException("Usuario no encontrado con ID: " + usuarioId);
        }
        carritoRepository.deleteByUsuarioId(usuarioId);
    }

    // validacion
    private Producto validarProductoYStock(Long productoId, Integer cantidad) {
        if (cantidad == null || cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }

        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + productoId));

        if (producto.getStock() <= 0) {
            throw new IllegalStateException("Producto sin stock disponible");
        }
        if (cantidad > producto.getStock()) {
            throw new IllegalStateException("La cantidad solicitada (" + cantidad + ") excede el stock disponible (" + producto.getStock() + ")");
        }

        return producto;
    }
}
