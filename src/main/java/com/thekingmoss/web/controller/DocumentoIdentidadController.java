package com.thekingmoss.web.controller;

import com.thekingmoss.application.dto.documentoIdentidad.DocumentoIdentidadRequestDto;
import com.thekingmoss.application.dto.documentoIdentidad.DocumentoIdentidadResponseDto;
import com.thekingmoss.application.service.IDocumentoIdentidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/documentoIdentidad")
public class DocumentoIdentidadController {
    private final IDocumentoIdentidadService documentoIdentidadService;

    @GetMapping
    public List<DocumentoIdentidadResponseDto> listarDocumentoIdentidad() {
        return documentoIdentidadService.listarDocumentoIdentidad();
    }

    @GetMapping("/{id}")
    public DocumentoIdentidadResponseDto buscarDocumentoIdentidadPorId(@PathVariable Long id) {
        return documentoIdentidadService.buscarDocumentoIdentidadPorId(id);
    }

    @PostMapping
    public DocumentoIdentidadResponseDto guardarDocumentoIdentidad(@RequestBody DocumentoIdentidadRequestDto requestDto) {
        return documentoIdentidadService.guardarDocumentoIdentidad(requestDto);
    }

    @PutMapping("/{id}")
    public DocumentoIdentidadResponseDto actualizarDocumentoIdentidad(@PathVariable Long id, @RequestBody DocumentoIdentidadRequestDto requestDto) {
        return documentoIdentidadService.actualizarDocumentoIdentidad(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public void eliminarDocumentoIdentidad(@PathVariable Long id) {
        documentoIdentidadService.eliminarDocumentoIdentidad(id);
    }
}
