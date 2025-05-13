package com.thekingmoss.application.dto.categoria;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoriaRequestDto {
    private Long categoriaId;
    private String nombreCategoria;
}
