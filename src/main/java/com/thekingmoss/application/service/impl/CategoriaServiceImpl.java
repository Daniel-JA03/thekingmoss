package com.thekingmoss.application.service.impl;

import com.thekingmoss.application.dto.categoria.CategoriaRequestDto;
import com.thekingmoss.application.dto.categoria.CategoriaResponseDto;
import com.thekingmoss.application.mapper.categoria.CategoriaMapper;
import com.thekingmoss.application.service.ICategoriaService;
import com.thekingmoss.domain.entity.Categoria;
import com.thekingmoss.domain.repository.ICategoriaRepository;
import com.thekingmoss.web.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements ICategoriaService {
    private final ICategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    @Override
    public List<CategoriaResponseDto> listarCategorias() {
        return categoriaRepository.findAll().stream()
                .map(categoriaMapper::toDto)
                .toList();
    }

    @Override
    public CategoriaResponseDto buscarCategoriaPorId(Long id) {
        return categoriaRepository.findById(id)
                .map(categoriaMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada" + id));
    }

    @Override
    public CategoriaResponseDto crearCategoria(CategoriaRequestDto requestDto) {
        Categoria categoria = categoriaMapper.toEntity(requestDto);
        return categoriaMapper.toDto(categoriaRepository.save(categoria));
    }

    @Override
    public CategoriaResponseDto actualizarCategoria(Long id, CategoriaRequestDto requestDto) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada" + id));
        categoria.setNombreCategoria(requestDto.getNombreCategoria());

        Categoria actualizarCategoria = categoriaRepository.save(categoria);
        return categoriaMapper.toDto(actualizarCategoria);
    }

    @Override
    public void eliminarCategoria(Long id) {
        if(!categoriaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Categoria no encontrada" + id);
        }
        categoriaRepository.deleteById(id);
    }
}
