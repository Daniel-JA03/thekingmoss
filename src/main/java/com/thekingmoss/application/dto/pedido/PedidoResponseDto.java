package com.thekingmoss.application.dto.pedido;

import com.thekingmoss.application.dto.detallePedido.DetallePedidoResponseDto;
import com.thekingmoss.domain.entity.types.TipoEstadoPedido;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class PedidoResponseDto {
    private Long pedidoId;
    private Date fechaPedido;
    private String tipoEntrega;
    private String informacionPedido;
    private String instruccionEntrega;
    private TipoEstadoPedido tipoEstadoPedido;
    private Long usuarioId;

    @Builder.Default
    private List<DetallePedidoResponseDto> detalle = List.of();
}
