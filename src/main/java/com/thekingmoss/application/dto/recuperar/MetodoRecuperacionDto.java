package com.thekingmoss.application.dto.recuperar;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MetodoRecuperacionDto {
    private Long usuarioId;
    private String username;
    private String emailMasked;
    private String telefonoMasked;
}
