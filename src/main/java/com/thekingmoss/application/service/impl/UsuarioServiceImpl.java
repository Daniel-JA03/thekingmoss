package com.thekingmoss.application.service.impl;

import com.thekingmoss.application.dto.usuario.UsuarioResponseDto;
import com.thekingmoss.application.mapper.usuario.UsuarioMapper;
import com.thekingmoss.application.service.IUsuarioService;
import com.thekingmoss.domain.entity.Usuario;
import com.thekingmoss.domain.repository.IUsuarioRepository;
import com.thekingmoss.web.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements IUsuarioService {
    private final IUsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Override
    public List<UsuarioResponseDto> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(usuarioMapper::toDto)
                .toList();
    }

    @Override
    public UsuarioResponseDto buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(usuarioMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado" + id));
    }

    @Override
    public void toggleUserStatus(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado" + id));

        usuario.setAccountLocked(!usuario.isAccountLocked());

        if(!usuario.isAccountLocked()) {
            usuario.setFailedAttempts(0); // Reiniciar intentos fallidos al desbloquear
        }

        usuarioRepository.save(usuario);


    }
}
