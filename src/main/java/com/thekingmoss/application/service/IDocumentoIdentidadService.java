package com.thekingmoss.application.service;

import com.thekingmoss.application.dto.documentoIdentidad.DocumentoIdentidadRequestDto;
import com.thekingmoss.application.dto.documentoIdentidad.DocumentoIdentidadResponseDto;

import java.util.List;

public interface IDocumentoIdentidadService {
    List<DocumentoIdentidadResponseDto> listarDocumentoIdentidad();
    DocumentoIdentidadResponseDto buscarDocumentoIdentidadPorId(Long id);
    DocumentoIdentidadResponseDto guardarDocumentoIdentidad(DocumentoIdentidadRequestDto requestDto);
    DocumentoIdentidadResponseDto actualizarDocumentoIdentidad(Long id, DocumentoIdentidadRequestDto requestDto);
    void eliminarDocumentoIdentidad(Long id);
}
