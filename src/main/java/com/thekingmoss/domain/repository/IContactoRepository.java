package com.thekingmoss.domain.repository;

import com.thekingmoss.domain.entity.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContactoRepository extends JpaRepository<Contacto, Integer> {
}
