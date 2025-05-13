package com.thekingmoss.application.dto.documentoIdentidad;

import com.thekingmoss.domain.entity.types.TipoDocumentoIdentidad;
import lombok.Data;

@Data
public class DocumentoRequestDto {
    private String numeroDocumentoIdentidad;
    private TipoDocumentoIdentidad tipoDocumentoIdentidad;
    private String usuarioId;
}
