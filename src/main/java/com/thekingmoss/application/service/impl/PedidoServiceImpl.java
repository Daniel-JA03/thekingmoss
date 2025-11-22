package com.thekingmoss.application.service.impl;

import com.thekingmoss.application.dto.detallePedido.DetallePedidoRequestDto;
import com.thekingmoss.application.dto.detallePedido.DetallePedidoResponseDto;
import com.thekingmoss.application.dto.pedido.PedidoRequestDto;
import com.thekingmoss.application.dto.pedido.PedidoResponseDto;
import com.thekingmoss.application.mapper.detallePedido.DetallePedidoMapper;
import com.thekingmoss.application.mapper.pedido.PedidoMapper;
import com.thekingmoss.application.service.IPedidoService;
import com.thekingmoss.domain.entity.*;
import com.thekingmoss.domain.repository.*;
import com.thekingmoss.web.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements IPedidoService {
    private final IPedidoRepository pedidoRepository;
    private final IUsuarioRepository usuarioRepository;
    private final IProductoRepository productoRepository;
    private final IDetallePedidoRepository detallePedidoRepository;
    private final PedidoMapper pedidoMapper;
    private final DetallePedidoMapper detallePedidoMapper;
    private final ICarritoRepository carritoRepository;

    @Override
    @Transactional
    public PedidoResponseDto crearPedido(PedidoRequestDto requestDto) {
        Usuario usuario = usuarioRepository.findById(requestDto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Pedido pedido = pedidoMapper.toEntity(requestDto, usuario);
        Pedido guardarPedido = pedidoRepository.save(pedido);

        List<DetallePedido> detallePedidos = requestDto.getDetalles().stream()
                .map(detalleDto -> {
                    Producto producto = productoRepository.findById(detalleDto.getProductoId())
                            .orElseThrow(() -> new ResourceNotFoundException("No existe el producto: " + detalleDto.getProductoId()));

                    // validar stock
                    if (producto.getStock() < detalleDto.getCantidad()) {
                        throw new ResourceNotFoundException("Stock insuficiente para el producto: " + producto.getNombre());
                    }

                    // reducir stock
                    producto.setStock(producto.getStock() - detalleDto.getCantidad());
                    productoRepository.save(producto); // guardar producto con stock actualizado

                    return detallePedidoMapper.toEntity(detalleDto, guardarPedido, producto);
                })
                .collect(Collectors.toList());
        detallePedidoRepository.saveAll(detallePedidos);

        // vaciar el carrito del usuario después de crear el pedido
        carritoRepository.deleteByUsuarioId(usuario.getId());

        List<DetallePedidoResponseDto> detalleDto = detallePedidos.stream()
                .map(detallePedidoMapper::toDto)
                .collect(Collectors.toList());

        PedidoResponseDto responseDto = pedidoMapper.toDto(guardarPedido);
        responseDto.setDetalle(detalleDto);

        return responseDto;
    }

    @Override
    @Transactional(readOnly = true)
    public PedidoResponseDto buscarPedidoPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado" + id));
        return pedidoMapper.toDto(pedido);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PedidoResponseDto> listarPedidos() {
        return pedidoRepository.findAll().stream()
                .map(pedidoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PedidoResponseDto actualizarPedido(Long id, PedidoRequestDto requestDto) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado" + id));
        Usuario usuario = usuarioRepository.findById(requestDto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        pedido.setFechaPedido(requestDto.getFechaPedido());
        pedido.setTipoEstadoPedido(requestDto.getTipoEstadoPedido());
        pedido.setInformacionPedido(requestDto.getInformacionPedido());
        pedido.setInstruccionEntrega(requestDto.getInstruccionEntrega());
        pedido.setTipoEstadoPedido(requestDto.getTipoEstadoPedido());
        pedido.setUsuario(usuario);
        return pedidoMapper.toDto(pedidoRepository.save(pedido));
    }

    @Override
    @Transactional
    public void eliminarPedido(Long id) {
        if(!pedidoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Pedido no encontrado" + id);
        }
        pedidoRepository.deleteById(id); // Hibernate borra los detalles automáticamente
    }

    @Override
    @Transactional
    public DetallePedidoResponseDto agregarDetallePedido(Long pedidoId, DetallePedidoRequestDto requestDto) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado" + pedidoId));
        Producto producto = productoRepository.findById(requestDto.getProductoId())
                .orElseThrow(() -> new ResourceNotFoundException("No existe el producto: " + requestDto.getProductoId()));
        DetallePedido detallePedido = detallePedidoMapper.toEntity(requestDto, pedido, producto);
        return detallePedidoMapper.toDto(detallePedidoRepository.save(detallePedido));
    }

    @Override
    @Transactional
    public void eliminarDetallePedido(Long pedidoId, Long productoId) {
        DetallePedidoId detallePedidoId = new DetallePedidoId(pedidoId, productoId);
        if(!detallePedidoRepository.existsById(detallePedidoId))
            throw new ResourceNotFoundException("Pedido no encontrado" + detallePedidoId);
        detallePedidoRepository.deleteById(detallePedidoId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PedidoResponseDto> listarPedidosPorUsuario(Long usuarioId) {
        // Validar que el  usuario existe
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new ResourceNotFoundException("Usuario no encontrado con ID: " + usuarioId);
        }

        // Obtener pedidos del usuario
        List<Pedido> pedidos = pedidoRepository.findByUsuarioId(usuarioId);

        // convertir a DTO
        return pedidos.stream()
                .map(pedidoMapper::toDto)
                .collect(Collectors.toList());
    }
}
