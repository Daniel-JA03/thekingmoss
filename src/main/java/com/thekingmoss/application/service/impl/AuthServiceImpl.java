package com.thekingmoss.application.service.impl;

import com.thekingmoss.application.dto.login.LoginRequestDto;
import com.thekingmoss.application.dto.login.LoginResponseDto;
import com.thekingmoss.application.dto.registrar.RegistrarRequestDto;
import com.thekingmoss.application.service.IAuthService;
import com.thekingmoss.domain.entity.Rol;
import com.thekingmoss.domain.entity.Usuario;
import com.thekingmoss.domain.repository.IRolRepository;
import com.thekingmoss.domain.repository.IUsuarioRepository;
import com.thekingmoss.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    private final IRolRepository rolRepository;
    private final IUsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginResponseDto authenticate(LoginRequestDto loginRequestDto) {
        authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                      loginRequestDto.getUsername(),
                      loginRequestDto.getPassword()
              )
        );
        UserDetails user = userDetailsService.loadUserByUsername(loginRequestDto.getUsername());
        String token = jwtUtil.generateToken(user);
        long expiration = jwtUtil.extractExpiration(token).getTime();
        return LoginResponseDto.builder()
                .token(token)
                .username(user.getUsername())
                .roles(user.getAuthorities().stream()
                        .map(r -> r.getAuthority())
                        .toList())
                .expirateAt(expiration)
                .build();
    }

    @Override
    public String register(RegistrarRequestDto registrarRequestDto) {
        if (usuarioRepository.existsByUsername(registrarRequestDto.getUsername())) {
            throw new RuntimeException("Usuario ya existe");
        }
        Rol rolUser = rolRepository.findById("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("No existe el rol"));
        Usuario usuario = Usuario.builder()
                .username(registrarRequestDto.getUsername())
                .password(passwordEncoder.encode(registrarRequestDto.getPassword()))
                .roles(Set.of(rolUser))
                .build();
        usuarioRepository.save(usuario);
        return "Usuario Registrado exitosamente";
    }
}
