package com.thekingmoss.application.mapper.categoria;

import com.thekingmoss.application.dto.categoria.CategoriaRequestDto;
import com.thekingmoss.application.dto.categoria.CategoriaResponseDto;
import com.thekingmoss.domain.entity.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {
    public CategoriaResponseDto toDto(Categoria categoria) {
        return CategoriaResponseDto.builder()
                .categoriaId(categoria.getCategoriaId())
                .nombreCategoria(categoria.getNombreCategoria())
                .build();
    }
    public Categoria toEntity(CategoriaRequestDto requestDto) {
        return Categoria.builder()
                .nombreCategoria(requestDto.getNombreCategoria())
                .build();
    }

    public Categoria toEntityCategoria(CategoriaResponseDto responseDto) {
        return Categoria.builder()
                .categoriaId(responseDto.getCategoriaId())
                .nombreCategoria(responseDto.getNombreCategoria())
                .build();
    }
}
