package com.thekingmoss.web.controller;

import com.thekingmoss.application.dto.login.LoginRequestDto;
import com.thekingmoss.application.dto.login.LoginResponseDto;
import com.thekingmoss.application.dto.recuperar.EnviarCodigoDto;
import com.thekingmoss.application.dto.recuperar.MetodoRecuperacionDto;
import com.thekingmoss.application.dto.recuperar.RecuperacionRequestDto;
import com.thekingmoss.application.dto.recuperar.VerificarCodigoDto;
import com.thekingmoss.application.dto.registrar.RegistrarRequestDto;
import com.thekingmoss.application.dto.usuario.UsuarioResponseDto;
import com.thekingmoss.application.service.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final IAuthService service;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        LoginResponseDto responseDto = service.authenticate(loginRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegistrarRequestDto request) {
        service.register(request);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuario registrado exitosamente");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/buscar-cuenta")
    public ResponseEntity<MetodoRecuperacionDto> buscarCuenta(@RequestBody RecuperacionRequestDto dto) {
        return ResponseEntity.ok(service.buscarCuenta(dto.getDato()));
    }

    @PostMapping("/enviar-codigo")
    public ResponseEntity<?> enviarCodigo(@RequestBody EnviarCodigoDto dto) {
        service.enviarCodigoRecuperacion(dto);
        return ResponseEntity.ok(Map.of("message", "Código enviado"));
    }

    @PostMapping("/verificar-codigo")
    public ResponseEntity<?> verificarCodigo(@RequestBody VerificarCodigoDto dto) {

        service.verificarCodigo(dto.getUsuarioId(), dto.getCodigo());

        return ResponseEntity.ok(Map.of("message", "Código válido"));
    }
}
