package com.thekingmoss.application.service;

import com.thekingmoss.application.dto.categoria.CategoriaRequestDto;
import com.thekingmoss.application.dto.categoria.CategoriaResponseDto;

import java.util.List;

public interface ICategoriaService {
    List<CategoriaResponseDto> listarCategorias();
    CategoriaResponseDto buscarCategoriaPorId(Long id);
    CategoriaResponseDto crearCategoria(CategoriaRequestDto requestDto);
    CategoriaResponseDto actualizarCategoria(Long id, CategoriaRequestDto requestDto);
    void eliminarCategoria(Long id);
}
