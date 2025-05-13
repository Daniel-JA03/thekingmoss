package com.thekingmoss.application.dto.usuario;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UsuarioResponseDto {
    private Long id;
    private String username;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String telefono;
    private String email;
    private List<String> roles;
}
