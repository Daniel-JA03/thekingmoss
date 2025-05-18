package com.thekingmoss.web.controller;

import com.thekingmoss.application.dto.detallePedido.DetallePedidoRequestDto;
import com.thekingmoss.application.dto.detallePedido.DetallePedidoResponseDto;
import com.thekingmoss.application.service.IDetallePedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos/{pedidoId}/detalles")
@RequiredArgsConstructor
public class DetallePedidoController {
    private final IDetallePedidoService detallePedidoService;

    @GetMapping
    public ResponseEntity<List<DetallePedidoResponseDto>> listarDetallesPorPedidoId(@PathVariable Long pedidoId) {
        return ResponseEntity.ok(detallePedidoService.listarDetallePorPedidoId(pedidoId));
    }

    @PutMapping("/{productoId}")
    public ResponseEntity<DetallePedidoResponseDto> actualizarDetallePedido(@PathVariable Long pedidoId, @PathVariable Long detalleId, @RequestBody DetallePedidoRequestDto dto) {
        return ResponseEntity.ok(detallePedidoService.actualizarDetallePedido(pedidoId, detalleId, dto));
    }
}
