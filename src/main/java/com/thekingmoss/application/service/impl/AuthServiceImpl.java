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
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {
    // constante para el número máximo de intentos fallidos
    private static final int MAX_FAILED_ATTEMPTS = 3;

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    private final IRolRepository rolRepository;
    private final IUsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginResponseDto authenticate(LoginRequestDto loginRequestDto) {
        // Controlar intentos fallidos y bloqueo de cuenta

        // obtener el usuario completo para obtener el ID
        Usuario usuario = usuarioRepository.findByUsername(loginRequestDto.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // verificar si la cuenta está bloqueada
        if (usuario.isAccountLocked()) {
            throw new IllegalStateException("Cuenta bloqueada. Contáctese con el administrador.");
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDto.getUsername(),
                            loginRequestDto.getPassword()
                    )
            );
        } catch (BadCredentialsException ex) {
            int attempts = usuario.getFailedAttempts() + 1;
            usuario.setFailedAttempts(attempts);

            int remainingAttempts = MAX_FAILED_ATTEMPTS - attempts; // para mostrar al usuario cuántos intentos le quedan

            if (attempts >= MAX_FAILED_ATTEMPTS) {
                usuario.setAccountLocked(true);
                usuarioRepository.save(usuario);
                throw new IllegalStateException(
                        "Cuenta bloqueada por múltiples intentos fallidos. Contáctese con el administrador."
                );
            }

            usuarioRepository.save(usuario);

            throw new IllegalArgumentException(
                    "Credenciales incorrectas. Te quedan " + remainingAttempts +
                            " intentos antes de que la cuenta sea bloqueada."
            );
        }

        // login correcto, resetear intentos fallidos
        usuario.setFailedAttempts(0);
        usuarioRepository.save(usuario);

        UserDetails user = userDetailsService.loadUserByUsername(loginRequestDto.getUsername());
        String token = jwtUtil.generateToken(user);
        long expiration = jwtUtil.extractExpiration(token).getTime();

        return LoginResponseDto.builder()
                .token(token)
                .username(user.getUsername())
                .email(usuario.getEmail())
                .roles(user.getAuthorities().stream()
                        .map(r -> r.getAuthority())
                        .toList())
                .usuarioId(usuario.getId())
                .expirateAt(expiration)
                .build();
    }

    @Override
    public String register(RegistrarRequestDto registrarRequestDto) {
        // validamos que el username no exista
        if (usuarioRepository.existsByUsername(registrarRequestDto.getUsername())) {
            throw new RuntimeException("El nombre de usuario ya está en uso");
        }

        // validamos que el email no exista
        if(usuarioRepository.existsByEmail(registrarRequestDto.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }

        // Obtener rol por defecto (USER)
        Rol rolUser = rolRepository.findById("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("No USER no encontrado"));

        // Construir el usuario
        Usuario usuario = Usuario.builder()
                .username(registrarRequestDto.getUsername())
                .password(passwordEncoder.encode(registrarRequestDto.getPassword()))
                .nombreUsuario(registrarRequestDto.getNombreUsuario())
                .apellidoUsuario(registrarRequestDto.getApellidoUsuario())
                .telefono(registrarRequestDto.getTelefono())
                .email(registrarRequestDto.getEmail())
                .roles(Set.of(rolUser))
                // inicializamos los campos de seguridad
                .failedAttempts(0)
                .accountLocked(false)
                .build();
        usuarioRepository.save(usuario);
        return "Usuario Registrado exitosamente";
    }

}
