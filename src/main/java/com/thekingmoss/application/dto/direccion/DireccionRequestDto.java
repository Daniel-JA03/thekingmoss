package com.thekingmoss.application.dto.direccion;

import com.thekingmoss.domain.entity.types.TipoDireccion;
import lombok.Data;

@Data
public class DireccionRequestDto {
    private String pais;
    private String estado;
    private String provincia;
    private String distrito;
    private String referencia;
    private TipoDireccion tipoDireccion;
    private Long usuarioId;
}
