package com.thekingmoss.application.service.impl;

import com.thekingmoss.application.dto.documentoIdentidad.DocumentoIdentidadRequestDto;
import com.thekingmoss.application.dto.documentoIdentidad.DocumentoIdentidadResponseDto;
import com.thekingmoss.application.mapper.documentoIdentidad.DocumentoIdentidadMapper;
import com.thekingmoss.application.mapper.usuario.UsuarioMapper;
import com.thekingmoss.application.service.IDocumentoIdentidadService;
import com.thekingmoss.domain.entity.DocumentoIdentidad;
import com.thekingmoss.domain.entity.Usuario;
import com.thekingmoss.domain.repository.IDocumentoIdentidadRepository;
import com.thekingmoss.domain.repository.IUsuarioRepository;
import com.thekingmoss.web.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.Doc;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentoIdentidadServiceImpl implements IDocumentoIdentidadService {
    private final IDocumentoIdentidadRepository documentoIdentidadRepository;
    private final DocumentoIdentidadMapper documentoIdentidadMapper;
    private final IUsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;


    @Override
    public List<DocumentoIdentidadResponseDto> listarDocumentoIdentidad() {
        return documentoIdentidadRepository.findAll().stream()
                .map(documentoIdentidadMapper::toDto)
                .toList();
    }

    @Override
    public DocumentoIdentidadResponseDto buscarDocumentoIdentidadPorId(Long id) {
        return documentoIdentidadMapper.toDto(documentoIdentidadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Documento de identidad no encontrado " + id)));

    }

    @Override
    public DocumentoIdentidadResponseDto guardarDocumentoIdentidad(DocumentoIdentidadRequestDto requestDto) {
        Usuario usuario = usuarioRepository.findById(requestDto.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado " + requestDto.getUsuarioId()));
        DocumentoIdentidad documentoIdentidad = documentoIdentidadMapper.toEntity(requestDto, usuario);
        DocumentoIdentidad guardarDocumentoIdentidad = documentoIdentidadRepository.save(documentoIdentidad);
        return documentoIdentidadMapper.toDto(guardarDocumentoIdentidad);
    }

    @Override
    public DocumentoIdentidadResponseDto actualizarDocumentoIdentidad(Long id, DocumentoIdentidadRequestDto requestDto) {
        DocumentoIdentidad documentoIdentidad = documentoIdentidadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Documento no encontrado " + id));
        Usuario usuario = usuarioRepository.findById(requestDto.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado " + id));

        documentoIdentidad.setNumeroDocumentoIdentidad(requestDto.getNumeroDocumentoIdentidad());
        documentoIdentidad.setTipoDocumentoIdentidad(requestDto.getTipoDocumentoIdentidad());
        documentoIdentidad.setUsuario(usuario);
        DocumentoIdentidad actualizarDocumentoIdentidad = documentoIdentidadRepository.save(documentoIdentidad);

        return documentoIdentidadMapper.toDto(actualizarDocumentoIdentidad);
    }

    @Override
    @Transactional
    public void eliminarDocumentoIdentidad(Long id) {
        if(!documentoIdentidadRepository.existsById(id)) {
            throw new ResourceNotFoundException("Documento no encontrado " + id);
        }

        // Buscar el documento
        DocumentoIdentidad documentoIdentidad = documentoIdentidadRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Documento no encontrado: " + id));

        // Limpiar la referencia en Usuario
        Usuario usuario = documentoIdentidad.getUsuario();
        usuario.setDocumentoIdentidad(null); // eliminar la referencia inversa
        usuarioRepository.save(usuario); // Guardar cambio

        // elimina el documento
        documentoIdentidadRepository.deleteById(id);
    }
}
