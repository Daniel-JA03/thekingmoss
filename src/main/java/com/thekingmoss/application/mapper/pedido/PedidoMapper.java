package com.thekingmoss.application.mapper.pedido;

import com.thekingmoss.application.dto.detallePedido.DetallePedidoResponseDto;
import com.thekingmoss.application.dto.pedido.PedidoRequestDto;
import com.thekingmoss.application.dto.pedido.PedidoResponseDto;
import com.thekingmoss.domain.entity.DetallePedido;
import com.thekingmoss.domain.entity.Pedido;
import com.thekingmoss.domain.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PedidoMapper {
    public Pedido toEntity(PedidoRequestDto requestDto, Usuario usuario) {
        return Pedido.builder()
                .fechaPedido(requestDto.getFechaPedido())
                .tipoEntrega(requestDto.getTipoEntrega())
                .informacionPedido(requestDto.getInformacionPedido())
                .instruccionEntrega(requestDto.getInstruccionEntrega())
                .tipoEstadoPedido(requestDto.getTipoEstadoPedido())
                .usuario(usuario)
                .build();
    }
    public PedidoResponseDto toDto(Pedido pedido) {
        return PedidoResponseDto.builder()
                .pedidoId(pedido.getPedidoId())
                .fechaPedido(pedido.getFechaPedido())
                .tipoEntrega(pedido.getTipoEntrega())
                .informacionPedido(pedido.getInformacionPedido())
                .instruccionEntrega(pedido.getInstruccionEntrega())
                .tipoEstadoPedido(pedido.getTipoEstadoPedido())
                .usuarioId(pedido.getUsuario().getId())
                .detalle(pedido.getDetallePedidos().stream()
                        .map(this::toDetalleDto)
                        .collect(Collectors.toList())
                )
                .build();
    }
    private DetallePedidoResponseDto toDetalleDto(DetallePedido detallePedido) {
        return DetallePedidoResponseDto.builder()
                .productoId(detallePedido.getProducto().getProductoId())
                .nombreProducto(detallePedido.getProducto().getNombre())
                .cantidad(detallePedido.getCantidad())
                .precioUnitario(detallePedido.getProducto().getPrecioUnitario())
                .descuento(detallePedido.getProducto().getDescuento())
                .build();
    }
}
