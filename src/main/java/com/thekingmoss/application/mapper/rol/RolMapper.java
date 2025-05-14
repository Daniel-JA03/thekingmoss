package com.thekingmoss.application.mapper.rol;

import com.thekingmoss.application.dto.rol.RolRequestDto;
import com.thekingmoss.application.dto.rol.RolResponseDto;
import com.thekingmoss.domain.entity.Rol;
import org.springframework.stereotype.Component;

@Component
public class RolMapper {
    public RolResponseDto toDto(Rol rol) {
        if (rol == null) return null;
        return RolResponseDto.builder()
                .idRol(rol.getName())
                .build();
    }

    public Rol toEntity(RolRequestDto rolRequestDto) {
        if (rolRequestDto == null) return null;
        return Rol.builder()
                .name(rolRequestDto.getDescripcion())
                .build();
    }

    public Rol toEntityRol(RolResponseDto rolResponseDto) {
        if (rolResponseDto == null) return null;
        return Rol.builder()
                .name(rolResponseDto.getIdRol())
                .build();
    }
}
