package com.thekingmoss.application.dto.documentoIdentidad;

import com.thekingmoss.domain.entity.types.TipoDocumentoIdentidad;
import lombok.Data;

@Data
public class DocumentoIdentidadRequestDto {
    private String numeroDocumentoIdentidad;
    private TipoDocumentoIdentidad tipoDocumentoIdentidad;
    private Long usuarioId;
}
