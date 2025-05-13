package com.thekingmoss.application.dto.usuario;

import lombok.Data;

import java.util.Set;

@Data
public class UsuarioRequestDto {
    private String username;
    private String password;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String telefono;
    private String email;
    private Set<String> roles;
}
