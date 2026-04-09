package com.thekingmoss.application.dto.recuperar;

import lombok.Data;

@Data
public class VerificarCodigoDto {
    private Long usuarioId;
    private String codigo;
}
