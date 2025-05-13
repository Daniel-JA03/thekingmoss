package com.thekingmoss.application.dto.documentoIdentidad;

import com.thekingmoss.domain.entity.types.TipoDocumentoIdentidad;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DocumentoIdentidadResponseDto {
    private Long documentoIdentidadId;
    private String numeroDocumentoIdentidad;
    private TipoDocumentoIdentidad tipoDocumentoIdentidad;
    // Usuario
    private String usuarioId;
    private String username;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String telefono;
    private String email;
    // Rol
    private Long rolId;
    private String nombreRol;
}
