package com.thekingmoss.web.controller;

import com.thekingmoss.application.dto.usuario.UsuarioResponseDto;
import com.thekingmoss.application.service.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final IUsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @GetMapping("/{id}")
     public ResponseEntity<UsuarioResponseDto> buscarUsuarioPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorId(id));
    }

    @PutMapping("/{id}/toggle-lock")
    public ResponseEntity<Map<String,String>> toggleLock(@PathVariable Long id){

        usuarioService.toggleUserStatus(id);

        Map<String,String> response = new HashMap<>();
        response.put("message","Usuario desbloqueado correctamente");

        return ResponseEntity.ok(response);
    }
}
