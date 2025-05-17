package com.thekingmoss.web.controller;

import com.thekingmoss.application.dto.categoria.CategoriaRequestDto;
import com.thekingmoss.application.dto.categoria.CategoriaResponseDto;
import com.thekingmoss.application.service.ICategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoria")
@RequiredArgsConstructor
public class CategoriaController {
    private final ICategoriaService categoriaService;

    @GetMapping
    public List<CategoriaResponseDto> listarCategorias() {
        return categoriaService.listarCategorias();
    }

    @PostMapping
    public CategoriaResponseDto crearCategoria(@RequestBody CategoriaRequestDto requestDto) {
        return categoriaService.crearCategoria(requestDto);
    }

    @GetMapping("/{id}")
    public CategoriaResponseDto buscarCategoriaPorId(@PathVariable Long id) {
        return categoriaService.buscarCategoriaPorId(id);
    }

    @PutMapping("/{id}")
    public CategoriaResponseDto actualizarCategoria(@PathVariable Long id, @RequestBody CategoriaRequestDto requestDto) {
        return categoriaService.actualizarCategoria(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public void eliminarCategoria(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
    }
}
