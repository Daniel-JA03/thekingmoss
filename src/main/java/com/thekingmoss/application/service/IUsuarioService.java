package com.thekingmoss.application.service;

import com.thekingmoss.application.dto.usuario.UsuarioResponseDto;

import java.util.List;

public interface IUsuarioService {
    List<UsuarioResponseDto> listarUsuarios();
    UsuarioResponseDto buscarUsuarioPorId(Long id);
    void toggleUserStatus(Long id);
}
