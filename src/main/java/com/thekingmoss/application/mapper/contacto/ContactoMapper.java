package com.thekingmoss.application.mapper.contacto;

import com.thekingmoss.application.dto.contacto.ContactoRequestDto;
import com.thekingmoss.application.dto.contacto.ContactoResponseDto;
import com.thekingmoss.domain.entity.Contacto;
import org.springframework.stereotype.Component;

@Component
public class ContactoMapper {
    public ContactoResponseDto toDto(Contacto contacto) {
        return ContactoResponseDto.builder()
                .contactoid(contacto.getContactoid())
                .nombre(contacto.getNombre())
                .email(contacto.getEmail())
                .asunto(contacto.getAsunto())
                .mensaje(contacto.getMensaje())
                .estado(contacto.getEstado())
                .fechaCreacion(contacto.getFechaCreacion())
                .build();
    }

    public Contacto toEntity(ContactoRequestDto requestDto) {
        return Contacto.builder()
                .nombre(requestDto.getNombre())
                .email(requestDto.getEmail())
                .asunto(requestDto.getAsunto())
                .mensaje(requestDto.getMensaje())
                .estado(requestDto.getEstado())
                .build();
    }
}
