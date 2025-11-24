package com.thekingmoss.application.service.impl;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import com.thekingmoss.application.dto.payment.PaymentRequestDto;
import com.thekingmoss.application.dto.payment.PaymentResponseDto;
import com.thekingmoss.application.service.IPaymentService;
import com.thekingmoss.domain.entity.Pedido;
import com.thekingmoss.domain.repository.IPedidoRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements IPaymentService {

    @Value("${stripe.secret.key}")
    private String secretKey;

    private final IPedidoRepository pedidoRepository;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    @Override
    public PaymentResponseDto createPaymentIntent(PaymentRequestDto request) {
        try {
            // Obtener el pedido para calcular el monto
            Pedido pedido = pedidoRepository.findById(request.getPedidoId())
                    .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

            // Calcular monto total
            BigDecimal totalPedido = pedido.getDetallePedidos().stream()
                    .map(d -> d.getProducto().getPrecioUnitario()
                            .multiply(new BigDecimal(d.getCantidad()))) // precio * cantidad
                    .reduce(BigDecimal.ZERO, BigDecimal::add); // suma total

            // Convertir a centimos (S/ 50.00 -> 5000)
            long montoEnCentimos = totalPedido.multiply(new BigDecimal(100)).longValue();

            // Crear PaymentIntent
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount(montoEnCentimos)
                    .setCurrency("PEN") // Soles peruanos
                    .setDescription("Pago en TheKingMoss - Pedido #" + request.getPedidoId())
                    .setReceiptEmail(request.getEmail())
                    .putMetadata("pedidoId", request.getPedidoId().toString())
                    .build();

            PaymentIntent intent = PaymentIntent.create(params);

            return PaymentResponseDto.builder()
                    .clientSecret(intent.getClientSecret())
                    .build();

        } catch (StripeException e) {
            throw new RuntimeException("Error al crear el intento de pago: " + e.getMessage());
        }
    }
}
