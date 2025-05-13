package com.thekingmoss.application.dto.pedido;

import com.thekingmoss.domain.entity.types.TipoEstadoPedido;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

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
}
