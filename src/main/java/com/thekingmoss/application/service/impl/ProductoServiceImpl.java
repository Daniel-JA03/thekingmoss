package com.thekingmoss.application.service.impl;

import com.thekingmoss.application.dto.categoria.CategoriaResponseDto;
import com.thekingmoss.application.dto.producto.ProductoRequestDto;
import com.thekingmoss.application.dto.producto.ProductoResponseDto;
import com.thekingmoss.application.mapper.categoria.CategoriaMapper;
import com.thekingmoss.application.mapper.producto.ProductoMapper;
import com.thekingmoss.application.service.ICategoriaService;
import com.thekingmoss.application.service.IProductoService;
import com.thekingmoss.domain.entity.Categoria;
import com.thekingmoss.domain.entity.Producto;
import com.thekingmoss.domain.repository.IProductoRepository;
import com.thekingmoss.web.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements IProductoService {
    private final IProductoRepository productoRepository;
    private final ProductoMapper productoMapper;
    private final ICategoriaService categoriaService;
    private final CategoriaMapper categoriaMapper;


    @Override
    public List<ProductoResponseDto> listarProductos() {
        return productoRepository.findAll().stream()
                .map(productoMapper::toDto)
                .toList();
    }

    @Override
    public ProductoResponseDto buscarProductoPorId(Long id) {
        return productoRepository.findById(id)
                .map(productoMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado" + id));
    }

    @Override
    public List<ProductoResponseDto> listarProductoPorNombreCategoria(String nombreCategoria) {
        return productoRepository.findByCategoria_NombreCategoria(nombreCategoria).stream()
                .map(productoMapper::toDto)
                .toList();
    }

    @Override
    public ProductoResponseDto crearProducto(ProductoRequestDto requestDto) {
        CategoriaResponseDto categoriaResponseDto = categoriaService.buscarCategoriaPorId(requestDto.getCategoriaId());
        Categoria categoria = categoriaMapper.toEntityCategoria(categoriaResponseDto);
        Producto producto = productoMapper.toEntity(requestDto, categoria);
        return productoMapper.toDto(productoRepository.save(producto));
    }

    @Override
    public ProductoResponseDto actualizarProducto(Long id, ProductoRequestDto requestDto) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado" + id));
        CategoriaResponseDto categoriaResponseDto = categoriaService.buscarCategoriaPorId(requestDto.getCategoriaId());
        Categoria categoria = categoriaMapper.toEntityCategoria(categoriaResponseDto);

        producto.setNombre(requestDto.getNombreProducto());
        producto.setStock(requestDto.getStock());
        producto.setPrecioUnitario(requestDto.getPrecioUnitario());
        producto.setDescuento(requestDto.getDescuento());
        producto.setDescripcion(requestDto.getDescripcion());
        producto.setTamanio(requestDto.getTamanio());
        producto.setPeso(requestDto.getPeso());
        producto.setCategoria(categoria);
        return productoMapper.toDto(productoRepository.save(producto));
    }

    @Override
    public void eliminarProducto(Long id) {
        if(!productoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Producto no encontrado" + id);
        }
        productoRepository.deleteById(id);
    }
}
