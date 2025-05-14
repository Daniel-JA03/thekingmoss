package com.thekingmoss.application.dto.documentoIdentidad;

import com.thekingmoss.domain.entity.types.TipoDocumentoIdentidad;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DocumentoIdentidadResponseDto {
    private Long documentoIdentidadId;
    private String numeroDocumentoIdentidad;
    private TipoDocumentoIdentidad tipoDocumentoIdentidad;
    // Usuario
    private Long usuarioId;
    private String username;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String telefono;
    private String email;
    // Rol
    private List<String> nombreRoles;
}
