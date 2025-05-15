package com.thekingmoss.application.service;

import com.thekingmoss.application.dto.detallePedido.DetallePedidoRequestDto;
import com.thekingmoss.application.dto.detallePedido.DetallePedidoResponseDto;

import java.util.List;

public interface IDetallePedidoService {
    List<DetallePedidoResponseDto> listarDetallePorPedidoId(Long pedidoId);
    DetallePedidoResponseDto actualizarDetallePedido(Long pedidoId, Long productoId, DetallePedidoRequestDto requestDto);
}
