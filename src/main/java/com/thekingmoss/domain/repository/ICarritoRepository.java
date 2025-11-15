package com.thekingmoss.domain.repository;

import com.thekingmoss.domain.entity.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ICarritoRepository extends JpaRepository<Carrito, Long> {
    List<Carrito> findByUsuarioId(Long usuarioId);
    Optional<Carrito> findByUsuarioIdAndProductoProductoId(Long usuarioId, Long productoId);
    void deleteByUsuarioId(Long usuarioId);
}