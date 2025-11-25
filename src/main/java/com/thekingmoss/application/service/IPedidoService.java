package com.thekingmoss.application.service;

import com.thekingmoss.application.dto.detallePedido.DetallePedidoRequestDto;
import com.thekingmoss.application.dto.detallePedido.DetallePedidoResponseDto;
import com.thekingmoss.application.dto.payment.PaymentConfirmationRequestDto;
import com.thekingmoss.application.dto.pedido.PedidoRequestDto;
import com.thekingmoss.application.dto.pedido.PedidoResponseDto;

import java.util.List;

public interface IPedidoService {
    PedidoResponseDto crearPedido(PedidoRequestDto requestDto);
    PedidoResponseDto buscarPedidoPorId(Long id);
    List<PedidoResponseDto> listarPedidos();
    PedidoResponseDto actualizarPedido(Long id, PedidoRequestDto requestDto);
    void eliminarPedido(Long id);
    DetallePedidoResponseDto agregarDetallePedido(Long id, DetallePedidoRequestDto requestDto);
    void eliminarDetallePedido(Long pedidoId, Long productoId);

    List<PedidoResponseDto> listarPedidosPorUsuario(Long usuarioId);

    void confirmPayment(PaymentConfirmationRequestDto confirmationRequest);
}
