package com.thekingmoss.application.service.impl;

import com.thekingmoss.application.dto.contacto.ContactoRequestDto;
import com.thekingmoss.application.dto.contacto.ContactoResponseDto;
import com.thekingmoss.application.mapper.contacto.ContactoMapper;
import com.thekingmoss.application.service.IContactoService;
import com.thekingmoss.domain.entity.Contacto;
import com.thekingmoss.domain.entity.types.EstadoMensaje;
import com.thekingmoss.domain.repository.IContactoRepository;
import com.thekingmoss.web.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactoServiceImpl implements IContactoService {
    private final IContactoRepository contactoRepository;
    private final ContactoMapper contactoMapper;


    @Override
    public List<ContactoResponseDto> listarContactos() {
        return contactoRepository.findAll().stream()
                .map(contactoMapper::toDto)
                .toList();
    }

    @Override
    public ContactoResponseDto guardarContacto(ContactoRequestDto requestDto) {
        // Si el estado no viene, asignamos NUEVO por defecto
        if (requestDto.getEstado() == null) {
            requestDto.setEstado(EstadoMensaje.NUEVO);
        }

        Contacto contacto = contactoMapper.toEntity(requestDto);
        return contactoMapper.toDto(contactoRepository.save(contacto));
    }

    @Override
    public ContactoResponseDto actualizarEstado(Long id, EstadoMensaje estado) {
        Contacto contacto = contactoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mensaje de contacto no encontrado con id: " + id));
        contacto.setEstado(estado);
        return contactoMapper.toDto(contactoRepository.save(contacto));
    }
}
