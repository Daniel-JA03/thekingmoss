package com.thekingmoss.application.dto.contacto;

import com.thekingmoss.domain.entity.types.EstadoMensaje;
import lombok.Data;

@Data
public class ContactoRequestDto {
    private String nombre;
    private String email;
    private String asunto;
    private String mensaje;
    private EstadoMensaje estado;
}
