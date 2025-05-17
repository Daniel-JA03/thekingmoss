package com.thekingmoss.application.dto.registrar;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrarRequestDto {
    private String username;
    private String password;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String telefono;
    private String email;
}
