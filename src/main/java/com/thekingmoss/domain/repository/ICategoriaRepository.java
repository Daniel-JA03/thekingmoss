package com.thekingmoss.domain.repository;

import com.thekingmoss.domain.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {
}
