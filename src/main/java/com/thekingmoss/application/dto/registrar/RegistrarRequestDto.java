package com.thekingmoss.application.dto.registrar;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrarRequestDto {
    private String username;
    private String password;
}
