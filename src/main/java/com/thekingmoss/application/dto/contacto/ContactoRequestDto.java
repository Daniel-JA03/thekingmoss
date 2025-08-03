package com.thekingmoss.application.dto.contacto;

import lombok.Data;

@Data
public class ContactoRequestDto {
    private String nombre;
    private String email;
    private String asunto;
    private String mensaje;
}
