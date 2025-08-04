package com.thekingmoss.application.service;

import com.thekingmoss.application.dto.contacto.ContactoRequestDto;
import com.thekingmoss.application.dto.contacto.ContactoResponseDto;
import com.thekingmoss.domain.entity.types.EstadoMensaje;

import java.util.List;

public interface IContactoService {
    List<ContactoResponseDto> listarContactos();
    ContactoResponseDto guardarContacto(ContactoRequestDto requestDto);
    ContactoResponseDto actualizarEstado(Long id, EstadoMensaje estado);
}
