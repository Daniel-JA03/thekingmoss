package com.thekingmoss.domain.repository;

import com.thekingmoss.domain.entity.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDireccionRepository extends JpaRepository<Direccion, Long> {
    List<Direccion> findByUsuario_Id(long idUsuario);
}
