package com.thekingmoss.application.dto.direccion;

import com.thekingmoss.domain.entity.types.TipoDireccion;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DireccionResponseDto {
    private Long direccionId;
    private String pais;
    private String estado;
    private String provincia;
    private String distrito;
    private String referencia;
    private TipoDireccion tipoDireccion;
    //Usuario
    private Long usuarioId;
    private String username;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String telefono;
    private String email;
    //Rol
    private Long nombreRol;
}
