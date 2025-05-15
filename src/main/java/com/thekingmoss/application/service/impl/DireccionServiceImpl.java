package com.thekingmoss.application.service.impl;

import com.thekingmoss.application.dto.direccion.DireccionRequestDto;
import com.thekingmoss.application.dto.direccion.DireccionResponseDto;
import com.thekingmoss.application.mapper.direccion.DireccionMapper;
import com.thekingmoss.application.service.IDireccionService;
import com.thekingmoss.domain.entity.Direccion;
import com.thekingmoss.domain.entity.Usuario;
import com.thekingmoss.domain.repository.IDireccionRepository;
import com.thekingmoss.domain.repository.IUsuarioRepository;
import com.thekingmoss.web.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DireccionServiceImpl implements IDireccionService {
    private final IDireccionRepository direccionRepository;
    private final DireccionMapper direccionMapper;
    private final IUsuarioRepository usuarioRepository;


    @Override
    public List<DireccionResponseDto> listarDirecciones() {
        return direccionRepository.findAll().stream()
                .map(direccionMapper::toDto)
                .toList();
    }

    @Override
    public List<DireccionResponseDto> listarDireccionesPorUsuarioId(Long id) {
        return direccionRepository.findByUsuario_Id(id).stream()
                .map(direccionMapper::toDto)
                .toList();
    }

    @Override
    public DireccionResponseDto buscarDireccionPorId(Long id) {
        return direccionRepository.findById(id)
                .map(direccionMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Direccion no encontrado" + id));
    }

    @Override
    public DireccionResponseDto guardarDireccion(DireccionRequestDto requestDto) {
        Usuario usuario = usuarioRepository.findById(requestDto.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado" + requestDto.getUsuarioId()));
        Direccion direccion = direccionMapper.toEntity(requestDto, usuario);
        return direccionMapper.toDto(direccionRepository.save(direccion));
    }

    @Override
    public DireccionResponseDto actualizarDireccion(Long id, DireccionRequestDto requestDto) {
        Direccion direccion = direccionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Direccion no encontrado" + id));
        Usuario usuario = usuarioRepository.findById(requestDto.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado" + requestDto.getUsuarioId()));
        direccion.setPais(requestDto.getPais());
        direccion.setEstado(requestDto.getEstado());
        direccion.setProvincia(requestDto.getProvincia());
        direccion.setDistrito(requestDto.getDistrito());
        direccion.setReferencia(requestDto.getReferencia());
        direccion.setTipoDireccion(requestDto.getTipoDireccion());
        direccion.setUsuario(usuario);
        return direccionMapper.toDto(direccionRepository.save(direccion));
    }

    @Override
    public void eliminarDireccion(Long id) {
        if(!direccionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Direccion no encontrado" + id);
        }
        direccionRepository.deleteById(id);
    }
}
