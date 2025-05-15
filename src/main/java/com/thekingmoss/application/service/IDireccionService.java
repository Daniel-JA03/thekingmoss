package com.thekingmoss.application.service;

import com.thekingmoss.application.dto.direccion.DireccionRequestDto;
import com.thekingmoss.application.dto.direccion.DireccionResponseDto;

import java.util.List;

public interface IDireccionService {
    List<DireccionResponseDto> listarDirecciones();
    List<DireccionResponseDto> listarDireccionesPorUsuarioId(Long id);
    DireccionResponseDto buscarDireccionPorId(Long id);
    DireccionResponseDto guardarDireccion(DireccionRequestDto requestDto);
    DireccionResponseDto actualizarDireccion(Long id, DireccionRequestDto requestDto);
    void eliminarDireccion(Long id);
}
