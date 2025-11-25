package com.thekingmoss.application.dto.login;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LoginResponseDto {
    private String token;
    private String username;
    private String email;
    private List<String> roles;
    private Long usuarioId;
    private long expirateAt;
}
