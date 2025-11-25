package com.thekingmoss.web.controller;

import com.thekingmoss.application.dto.detallePedido.DetallePedidoRequestDto;
import com.thekingmoss.application.dto.detallePedido.DetallePedidoResponseDto;
import com.thekingmoss.application.dto.payment.PaymentConfirmationRequestDto;
import com.thekingmoss.application.dto.pedido.PedidoRequestDto;
import com.thekingmoss.application.dto.pedido.PedidoResponseDto;
import com.thekingmoss.application.service.IPedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {
    private final IPedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoResponseDto> crearPedido(@RequestBody PedidoRequestDto pedidoRequestDto) {
        return new ResponseEntity<>(pedidoService.crearPedido(pedidoRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDto> buscarPedidoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.buscarPedidoPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponseDto>> listarPedidos() {
        return ResponseEntity.ok(pedidoService.listarPedidos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponseDto> actualizarPedido(@PathVariable Long id ,@RequestBody PedidoRequestDto pedidoRequestDto) {
        return ResponseEntity.ok(pedidoService.actualizarPedido(id, pedidoRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id) {
        pedidoService.eliminarPedido(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{pedidoId}/detalles")
    public ResponseEntity<DetallePedidoResponseDto> agregarDetallePedido(@PathVariable Long pedidoId ,@RequestBody DetallePedidoRequestDto detallePedidoRequestDto) {
        return new ResponseEntity<>(pedidoService.agregarDetallePedido(pedidoId, detallePedidoRequestDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{pedidoId}/{productoId}")
    public ResponseEntity<Void> eliminarDetallePedido(@PathVariable Long pedidoId, @PathVariable Long productoId) {
        pedidoService.eliminarDetallePedido(pedidoId, productoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PedidoResponseDto>> listarPedidosPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(pedidoService.listarPedidosPorUsuario(usuarioId));
    }


    @PostMapping("/confirm-payment")
    public ResponseEntity<Void> confirmPayment(@RequestBody PaymentConfirmationRequestDto requestDto) {
        pedidoService.confirmPayment(requestDto);
        return ResponseEntity.ok().build();
    }
}
