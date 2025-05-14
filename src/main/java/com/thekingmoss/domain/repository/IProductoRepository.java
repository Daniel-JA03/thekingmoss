package com.thekingmoss.domain.repository;

import com.thekingmoss.domain.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByCategoria_NombreCategoria(String nombreCategoria);
}
