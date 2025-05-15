package com.thekingmoss.application.service.impl;

import com.thekingmoss.application.dto.detallePedido.DetallePedidoRequestDto;
import com.thekingmoss.application.dto.detallePedido.DetallePedidoResponseDto;
import com.thekingmoss.application.mapper.detallePedido.DetallePedidoMapper;
import com.thekingmoss.application.service.IDetallePedidoService;
import com.thekingmoss.domain.entity.DetallePedido;
import com.thekingmoss.domain.entity.DetallePedidoId;
import com.thekingmoss.domain.repository.IDetallePedidoRepository;
import com.thekingmoss.domain.repository.IPedidoRepository;
import com.thekingmoss.domain.repository.IProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DetallePedidoServiceImpl implements IDetallePedidoService {
    private final IPedidoRepository pedidoRepository;
    private final IProductoRepository productoRepository;
    private final IDetallePedidoRepository detallePedidoRepository;
    private final DetallePedidoMapper detallePedidoMapper;


    @Override
    @Transactional(readOnly = true)
    public List<DetallePedidoResponseDto> listarDetallePorPedidoId(Long pedidoId) {
        pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado" + pedidoId));

        return detallePedidoRepository.findAllByPedido_PedidoId(pedidoId).stream()
                .map(detallePedidoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DetallePedidoResponseDto actualizarDetallePedido(Long pedidoId, Long productoId, DetallePedidoRequestDto requestDto) {
        pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado" + pedidoId));
        productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado" + productoId));
        DetallePedidoId id = new DetallePedidoId(pedidoId, productoId);
        DetallePedido existing = detallePedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle Pedido no encontrado" + id));
        existing.setCantidad(requestDto.getCantidad());
        return detallePedidoMapper.toDto(detallePedidoRepository.save(existing));
    }
}
