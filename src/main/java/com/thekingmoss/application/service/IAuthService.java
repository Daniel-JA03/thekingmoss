package com.thekingmoss.application.service;

import com.thekingmoss.application.dto.login.LoginRequestDto;
import com.thekingmoss.application.dto.login.LoginResponseDto;
import com.thekingmoss.application.dto.recuperar.CambiarPasswordDto;
import com.thekingmoss.application.dto.recuperar.EnviarCodigoDto;
import com.thekingmoss.application.dto.recuperar.MetodoRecuperacionDto;
import com.thekingmoss.application.dto.registrar.RegistrarRequestDto;

public interface IAuthService {
    LoginResponseDto authenticate(LoginRequestDto loginRequestDto);
    String register(RegistrarRequestDto registrarRequestDto);
    MetodoRecuperacionDto buscarCuenta(String dato);
    String enviarCodigoRecuperacion(EnviarCodigoDto dto);
    void verificarCodigo(Long usuarioId, String codigo);
    void cambiarPassword(CambiarPasswordDto dto);
}
