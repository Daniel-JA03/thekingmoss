package com.thekingmoss.application.service;

import com.thekingmoss.application.dto.login.LoginRequestDto;
import com.thekingmoss.application.dto.login.LoginResponseDto;
import com.thekingmoss.application.dto.registrar.RegistrarRequestDto;

public interface IAuthService {
    LoginResponseDto authenticate(LoginRequestDto loginRequestDto);
    String register(RegistrarRequestDto registrarRequestDto);
}
