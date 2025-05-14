package com.thekingmoss.application.mapper.detallePedido;

import com.thekingmoss.application.dto.detallePedido.DetallePedidoRequestDto;
import com.thekingmoss.application.dto.detallePedido.DetallePedidoResponseDto;
import com.thekingmoss.domain.entity.DetallePedido;
import com.thekingmoss.domain.entity.DetallePedidoId;
import com.thekingmoss.domain.entity.Pedido;
import com.thekingmoss.domain.entity.Producto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DetallePedidoMapper {
    public DetallePedido toEntity(DetallePedidoRequestDto requestDto, Pedido pedido, Producto producto) {
        DetallePedidoId detallePedidoId = new DetallePedidoId(pedido.getPedidoId(), producto.getProductoId());
        return DetallePedido.builder()
                .detallePedidoId(detallePedidoId)
                .pedido(pedido)
                .producto(producto)
                .cantidad(requestDto.getCantidad())
                .build();
    }

    public DetallePedidoResponseDto toDto(DetallePedido detallePedido) {
        return DetallePedidoResponseDto.builder()
                .productoId(detallePedido.getProducto().getProductoId())
                .nombreProducto(detallePedido.getProducto().getNombre())
                .cantidad(detallePedido.getCantidad())
                .precioUnitario(detallePedido.getProducto().getPrecioUnitario())
                .descuento(detallePedido.getProducto().getDescuento())
                .build();
    }
}
