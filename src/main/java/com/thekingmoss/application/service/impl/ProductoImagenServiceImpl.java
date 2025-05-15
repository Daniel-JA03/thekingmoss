package com.thekingmoss.application.service.impl;

import com.thekingmoss.application.dto.producto.ProductoResponseDto;
import com.thekingmoss.application.dto.productoImagen.ProductoImagenRequestDto;
import com.thekingmoss.application.dto.productoImagen.ProductoImagenResponse;
import com.thekingmoss.application.mapper.producto.ProductoMapper;
import com.thekingmoss.application.mapper.productoImagen.ProductoImagenMapper;
import com.thekingmoss.application.service.IProductoImagenService;
import com.thekingmoss.application.service.IProductoService;
import com.thekingmoss.domain.entity.Producto;
import com.thekingmoss.domain.entity.ProductoImagen;
import com.thekingmoss.domain.repository.IProductoImagenRepository;
import com.thekingmoss.web.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoImagenServiceImpl implements IProductoImagenService {
    private final IProductoImagenRepository productoImagenRepository;
    private final IProductoService productoService;
    private final ProductoMapper productoMapper;
    private final ProductoImagenMapper productoImagenMapper;

    @Override
    public List<ProductoImagenResponse> listarProductoImagen() {
        return productoImagenRepository.findAll().stream()
                .map(productoImagenMapper::toDto)
                .toList();
    }

    @Override
    public ProductoImagenResponse crearProductoImagen(ProductoImagenRequestDto requestDto) {
        ProductoResponseDto productoResponseDto = productoService.buscarProductoPorId(requestDto.getProductoId());
        Producto producto = productoMapper.toEntityProducto(productoResponseDto);
        ProductoImagen productoImagen = productoImagenMapper.toEntity(requestDto, producto);
        return productoImagenMapper.toDto(productoImagenRepository.save(productoImagen));
    }

    @Override
    public ProductoImagenResponse buscarProductoImagenPorId(Long id) {
        return productoImagenRepository.findById(id)
                .map(productoImagenMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado" + id));
    }

    @Override
    public void eliminarProductoImagen(Long id) {
        if(!productoImagenRepository.existsById(id)) {
            throw new ResourceNotFoundException("Producto no encontrado" + id);
        }
        productoImagenRepository.deleteById(id);
    }
}
