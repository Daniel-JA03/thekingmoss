package com.thekingmoss.application.service;

import com.thekingmoss.application.dto.login.LoginRequestDto;
import com.thekingmoss.application.dto.login.LoginResponseDto;
import com.thekingmoss.application.dto.recuperar.EnviarCodigoDto;
import com.thekingmoss.application.dto.recuperar.MetodoRecuperacionDto;
import com.thekingmoss.application.dto.registrar.RegistrarRequestDto;
import com.thekingmoss.application.dto.usuario.UsuarioResponseDto;

public interface IAuthService {
    LoginResponseDto authenticate(LoginRequestDto loginRequestDto);
    String register(RegistrarRequestDto registrarRequestDto);
    MetodoRecuperacionDto buscarCuenta(String dato);
    void enviarCodigoRecuperacion(EnviarCodigoDto dto);
}
