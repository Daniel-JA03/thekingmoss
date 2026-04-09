package com.thekingmoss.application.dto.recuperar;

import lombok.Data;

@Data
public class EnviarCodigoDto {
    private Long usuarioId;
    private String metodo; // "EMAIL" o "SMS"
}
