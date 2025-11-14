package com.thekingmoss.domain.repository;

import com.thekingmoss.domain.entity.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ICarritoRepository extends JpaRepository<Carrito, Long> {
    List<Carrito> findByUsuarioIdUsuario(Long usuarioId);
    Optional<Carrito> findByUsuarioIdUsuarioAndProductoIdProducto(Long usuarioId, Long productoId);
    void deleteByUsuarioIdUsuario(Long usuarioId);
}
