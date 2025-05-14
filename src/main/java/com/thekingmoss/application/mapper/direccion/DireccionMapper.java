package com.thekingmoss.application.mapper.direccion;

import com.thekingmoss.application.dto.direccion.DireccionRequestDto;
import com.thekingmoss.application.dto.direccion.DireccionResponseDto;
import com.thekingmoss.domain.entity.Direccion;
import com.thekingmoss.domain.entity.Rol;
import com.thekingmoss.domain.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DireccionMapper {
    public DireccionResponseDto toDto(Direccion direccion) {
        return DireccionResponseDto.builder()
                .direccionId(direccion.getDireccionId())
                .pais(direccion.getPais())
                .estado(direccion.getEstado())
                .provincia(direccion.getProvincia())
                .distrito(direccion.getDistrito())
                .referencia(direccion.getReferencia())
                .tipoDireccion(direccion.getTipoDireccion())
                .usuarioId(direccion.getUsuario().getId())
                .username(direccion.getUsuario().getUsername())
                .nombreUsuario(direccion.getUsuario().getNombreUsuario())
                .apellidoUsuario(direccion.getUsuario().getApellidoUsuario())
                .telefono(direccion.getUsuario().getTelefono())
                .email(direccion.getUsuario().getEmail())
                .nombreRoles(direccion.getUsuario().getRoles().stream()
                        .map(Rol::getName)
                        .collect(Collectors.toList()))
                .build();
    }

    public Direccion toEntity(DireccionRequestDto requestDto, Usuario usuario) {
        return Direccion.builder()
                .pais(requestDto.getPais())
                .estado(requestDto.getEstado())
                .provincia(requestDto.getProvincia())
                .distrito(requestDto.getDistrito())
                .referencia(requestDto.getReferencia())
                .tipoDireccion(requestDto.getTipoDireccion())
                .usuario(usuario)
                .build();
    }
}
