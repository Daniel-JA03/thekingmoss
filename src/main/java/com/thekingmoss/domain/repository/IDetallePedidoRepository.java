package com.thekingmoss.domain.repository;

import com.thekingmoss.domain.entity.DetallePedido;
import com.thekingmoss.domain.entity.DetallePedidoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IDetallePedidoRepository extends JpaRepository<DetallePedido, DetallePedidoId> {
    List<DetallePedido> findAllByPedido_PedidoId(long pedidoId);
}
