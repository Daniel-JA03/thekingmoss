package com.thekingmoss.application.mapper.usuario;

import com.thekingmoss.application.dto.usuario.UsuarioRequestDto;
import com.thekingmoss.application.dto.usuario.UsuarioResponseDto;
import com.thekingmoss.domain.entity.Rol;
import com.thekingmoss.domain.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UsuarioMapper {

    public UsuarioResponseDto toDto(Usuario usuario) {
        if (usuario == null) return null;
        return UsuarioResponseDto.builder()
                .id(usuario.getId())
                .username(usuario.getUsername())
                .nombreUsuario(usuario.getNombreUsuario())
                .apellidoUsuario(usuario.getApellidoUsuario())
                .telefono(usuario.getTelefono())
                .email(usuario.getEmail())
                .roles(usuario.getRoles().stream()
                        .map(Rol::getName)
                        .collect(Collectors.toList()))
                .build();
    }

    public Usuario toEntity(UsuarioRequestDto requestDto, Set<Rol> roles) {
        if (requestDto == null) return null;
        return Usuario.builder()
                .username(requestDto.getUsername())
                .password(requestDto.getPassword())
                .nombreUsuario(requestDto.getNombreUsuario())
                .apellidoUsuario(requestDto.getApellidoUsuario())
                .telefono(requestDto.getTelefono())
                .email(requestDto.getEmail())
                .roles(roles)
                .build();
    }
}
