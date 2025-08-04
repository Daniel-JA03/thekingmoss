package com.thekingmoss.web.controller;

import com.thekingmoss.application.dto.contacto.ContactoRequestDto;
import com.thekingmoss.application.dto.contacto.ContactoResponseDto;
import com.thekingmoss.application.service.IContactoService;
import lombok.RequiredArgsConstructor;
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
}
