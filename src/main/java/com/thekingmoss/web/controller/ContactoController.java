package com.thekingmoss.web.controller;

import com.thekingmoss.application.dto.contacto.ContactoRequestDto;
import com.thekingmoss.application.dto.contacto.ContactoResponseDto;
import com.thekingmoss.application.service.IContactoService;
import com.thekingmoss.domain.entity.types.EstadoMensaje;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacto")
@RequiredArgsConstructor
public class ContactoController {
    private final IContactoService contactoService;

    @GetMapping
    public List<ContactoResponseDto> listarContactos() {
        return contactoService.listarContactos();
    }

    @PostMapping
    public ContactoResponseDto guardarContacto(@RequestBody ContactoRequestDto requestDto) {
        return contactoService.guardarContacto(requestDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactoResponseDto> actualizarEstado(@PathVariable Long id, @RequestParam EstadoMensaje estado) {

        ContactoResponseDto contactoActualizado = contactoService.actualizarEstado(id, estado);
        return ResponseEntity.ok(contactoActualizado);
    }
}
