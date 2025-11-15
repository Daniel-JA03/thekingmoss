package com.thekingmoss.application.mapper.carrito;

import com.thekingmoss.application.dto.carrito.CarritoRequestDto;
import com.thekingmoss.application.dto.carrito.CarritoResponseDto;
import com.thekingmoss.application.dto.carrito.ProductoCarritoDto;
import com.thekingmoss.domain.entity.Carrito;
import com.thekingmoss.domain.entity.Producto;
import com.thekingmoss.domain.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarritoMapper {
    private static final String BACKEND_URL = "http://localhost:8080";

    public CarritoResponseDto toDto(Carrito carrito) {
        if (carrito == null) return null;

        Producto p = carrito.getProducto();
        String imagenNombre = p.getProductoImagenes().isEmpty()
                ? "default.jpg"
                : p.getProductoImagenes().get(0).getImagenUrl();

        String imagenUrl = BACKEND_URL + "/imagesProducts/" + imagenNombre;

        ProductoCarritoDto productoDto = ProductoCarritoDto.builder()
                .productoId(p.getProductoId())
                .nombreProducto(p.getNombre())
                .imagenUrl(imagenUrl)
                .precioUnitario(p.getPrecioUnitario())
                .stock(p.getStock())
                .build();

        return CarritoResponseDto.builder()
                .idCarrito(carrito.getIdCarrito())
                .usuarioId(carrito.getUsuario().getId())
                .producto(productoDto)
                .cantidad(carrito.getCantidad())
                .build();
    }

    public List<CarritoResponseDto> toDtoList(List<Carrito> carritos) {
        return carritos.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Carrito toEntity(Usuario usuario, Producto producto, CarritoRequestDto request) {
        return Carrito.builder()
                .usuario(usuario)
                .producto(producto)
                .cantidad(request.getCantidad())
                .build();
    }
}
