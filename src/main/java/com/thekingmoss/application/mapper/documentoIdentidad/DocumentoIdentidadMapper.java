package com.thekingmoss.application.mapper.documentoIdentidad;

import com.thekingmoss.application.dto.documentoIdentidad.DocumentoIdentidadRequestDto;
import com.thekingmoss.application.dto.documentoIdentidad.DocumentoIdentidadResponseDto;
import com.thekingmoss.domain.entity.DocumentoIdentidad;
import com.thekingmoss.domain.entity.Rol;
import com.thekingmoss.domain.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DocumentoIdentidadMapper {
    public DocumentoIdentidadResponseDto toDto(DocumentoIdentidad documentoIdentidad) {
        return DocumentoIdentidadResponseDto.builder()
                .documentoIdentidadId(documentoIdentidad.getDocumentoIdentidadId())
                .numeroDocumentoIdentidad(documentoIdentidad.getNumeroDocumentoIdentidad())
                .tipoDocumentoIdentidad(documentoIdentidad.getTipoDocumentoIdentidad())
                .usuarioId(documentoIdentidad.getUsuario().getId())
                .username(documentoIdentidad.getUsuario().getUsername())
                .nombreUsuario(documentoIdentidad.getUsuario().getNombreUsuario())
                .apellidoUsuario(documentoIdentidad.getUsuario().getApellidoUsuario())
                .telefono(documentoIdentidad.getUsuario().getTelefono())
                .email(documentoIdentidad.getUsuario().getEmail())
                .nombreRoles(documentoIdentidad.getUsuario().getRoles().stream()
                        .map(Rol::getName)
                        .collect(Collectors.toList()))
                .build();
    }

    public DocumentoIdentidad toEntity(DocumentoIdentidadRequestDto requestDto, Usuario usuario) {
        return DocumentoIdentidad.builder()
                .numeroDocumentoIdentidad(requestDto.getNumeroDocumentoIdentidad())
                .tipoDocumentoIdentidad(requestDto.getTipoDocumentoIdentidad())
                .usuario(usuario)
                .build();
    }
}
