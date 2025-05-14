package com.thekingmoss.domain.repository;

import com.thekingmoss.domain.entity.DetallePedido;

import java.util.List;

public interface IDetallePedidoRepository {
    List<DetallePedido> findAllByPedido_PedidoId(long pedidoId);
}
