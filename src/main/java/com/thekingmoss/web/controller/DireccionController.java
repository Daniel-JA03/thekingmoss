package com.thekingmoss.web.controller;

import com.thekingmoss.application.dto.direccion.DireccionRequestDto;
import com.thekingmoss.application.dto.direccion.DireccionResponseDto;
import com.thekingmoss.application.service.IDireccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/direccion")
@RequiredArgsConstructor
public class DireccionController {
    private final IDireccionService direccionService;

    @GetMapping
    public List<DireccionResponseDto> listarDirecciones() {
        return direccionService.listarDirecciones();
    }

    @GetMapping("/usuario/{id}")
    public List<DireccionResponseDto> listarDireccionesPorUsuarioId(@PathVariable Long id) {
        return direccionService.listarDireccionesPorUsuarioId(id);
    }

    @GetMapping("/{id}")
    public DireccionResponseDto buscarDireccionPorId(@PathVariable Long id) {
        return direccionService.buscarDireccionPorId(id);
    }

    @PostMapping
    public DireccionResponseDto guardarDireccion(@RequestBody DireccionRequestDto requestDto) {
        return direccionService.guardarDireccion(requestDto);
    }

    @PutMapping("/{id}")
    public DireccionResponseDto actualizarDireccion(@PathVariable Long id, @RequestBody DireccionRequestDto requestDto) {
        return direccionService.actualizarDireccion(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public void eliminarDireccion(@PathVariable Long id) {
        direccionService.eliminarDireccion(id);
    }
}
