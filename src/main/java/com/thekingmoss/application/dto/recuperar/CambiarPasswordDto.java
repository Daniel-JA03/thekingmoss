package com.thekingmoss.application.dto.recuperar;

import lombok.Data;

@Data
public class CambiarPasswordDto {
    private Long usuarioId;
    private String nuevaPassword;
}
