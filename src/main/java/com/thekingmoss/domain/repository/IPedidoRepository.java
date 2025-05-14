package com.thekingmoss.domain.repository;

import com.thekingmoss.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPedidoRepository extends JpaRepository<Pedido, Long> {
}
