package com.thekingmoss.application.service.impl;

import com.thekingmoss.application.dto.producto.ProductoResponseDto;
import com.thekingmoss.application.dto.productoImagen.ProductoImagenRequestDto;
import com.thekingmoss.application.dto.productoImagen.ProductoImagenResponse;
import com.thekingmoss.application.mapper.producto.ProductoMapper;
import com.thekingmoss.application.mapper.productoImagen.ProductoImagenMapper;
import com.thekingmoss.application.service.IProductoImagenService;
import com.thekingmoss.application.service.IProductoService;
import com.thekingmoss.application.service.UploadFileService;
import com.thekingmoss.domain.entity.Producto;
import com.thekingmoss.domain.entity.ProductoImagen;
import com.thekingmoss.domain.repository.IProductoImagenRepository;
import com.thekingmoss.domain.repository.IProductoRepository;
import com.thekingmoss.web.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoImagenServiceImpl implements IProductoImagenService {
    private final IProductoImagenRepository productoImagenRepository;
    //private final IProductoService productoService;
    private final IProductoRepository productoRepository;
    private final ProductoMapper productoMapper;
    private final ProductoImagenMapper productoImagenMapper;

    private final UploadFileService upload;

    @Override
    public List<ProductoImagenResponse> listarProductoImagen() {
        return productoImagenRepository.findAll().stream()
                .map(productoImagenMapper::toDto)
                .toList();
    }

    @Override
    public ProductoImagenResponse crearProductoImagen(MultipartFile imagen, Long productoId) throws IOException {
        // Guardar la imagen
        String imagenUrl = upload.saveImage(imagen);

        // Buscar el producto DIRECTAMENTE desde el repositorio (sin usar el servicio)
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + productoId));

        // Crear la imagen
        ProductoImagen productoImagen = ProductoImagen.builder()
                .imagenUrl(imagenUrl)
                .producto(producto)
                .build();

        ProductoImagen creado = productoImagenRepository.save(productoImagen);
        return productoImagenMapper.toDto(creado);
    }

    @Override
    public ProductoImagenResponse buscarProductoImagenPorId(Long id) {
        return productoImagenRepository.findById(id)
                .map(productoImagenMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado" + id));
    }

    @Override
    @Transactional
    public ProductoImagenResponse actualizarProductoImagen(Long id, MultipartFile nuevaImagen, Long nuevoProductoId) throws IOException {
        // Busca la imagen existente
        ProductoImagen productoImagen = productoImagenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Imagen no encontrada con ID: " + id));

        // Elimina la imagen antigua si se proporciona una nueva
        if (nuevaImagen != null && !nuevaImagen.isEmpty()) {
            String nombreAntiguo = productoImagen.getImagenUrl().substring(productoImagen.getImagenUrl().lastIndexOf('/') + 1);
            upload.deleteImage(nombreAntiguo);
            String nuevaUrl = upload.saveImage(nuevaImagen);
            productoImagen.setImagenUrl(nuevaUrl);
        }

        // Busca el nuevo producto usando el repositorio (sin servicio)
        Producto producto = productoRepository.findById(nuevoProductoId)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + nuevoProductoId));

        // Actualiza el producto asociado
        productoImagen.setProducto(producto);

        // Guarda los cambios
        ProductoImagen actualizado = productoImagenRepository.save(productoImagen);
        return productoImagenMapper.toDto(actualizado);
    }


    @Override
    public void eliminarProductoImagen(Long id) {
        // Busca la imagen por ID
        ProductoImagen productoImagen = productoImagenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Imagen no encontrada con ID: " + id));

        // Extrae el nombre del archivo
        String nombreArchivo = productoImagen.getImagenUrl().substring(productoImagen.getImagenUrl().lastIndexOf('/') + 1);

        // Elimina el archivo físico (si no es la imagen predeterminada)
        if (!nombreArchivo.equals("default.jpg")) {
            upload.deleteImage(nombreArchivo);
        }

        // Elimina la entrada de la base de datos
        productoImagenRepository.deleteById(id);
    }
}
