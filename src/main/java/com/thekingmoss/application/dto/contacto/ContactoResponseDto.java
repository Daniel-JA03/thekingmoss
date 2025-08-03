package com.thekingmoss.application.dto.contacto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ContactoResponseDto {
    private Long contactoid;
    private String nombre;
    private String email;
    private String asunto;
    private String mensaje;
    private LocalDateTime fechaCreacion;
}
