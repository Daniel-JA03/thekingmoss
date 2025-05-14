package com.thekingmoss.application.dto.pedido;

import com.thekingmoss.application.dto.detallePedido.DetallePedidoRequestDto;
import com.thekingmoss.domain.entity.types.TipoEstadoPedido;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PedidoRequestDto {
    private Date fechaPedido;
    private String tipoEntrega;
    private String informacionPedido;
    private String instruccionEntrega;
    private TipoEstadoPedido tipoEstadoPedido;
    // Usuario
    private Long usuarioId;

    private List<DetallePedidoRequestDto> detalles;
}
