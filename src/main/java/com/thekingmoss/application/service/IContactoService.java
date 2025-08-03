package com.thekingmoss.application.service;

import com.thekingmoss.application.dto.contacto.ContactoRequestDto;
import com.thekingmoss.application.dto.contacto.ContactoResponseDto;

import java.util.List;

public interface IContactoService {
    List<ContactoResponseDto> listarContactos();
    ContactoResponseDto guardarContacto(ContactoRequestDto requestDto);
}
