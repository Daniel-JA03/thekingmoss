package com.thekingmoss.web.controller;

import com.thekingmoss.application.dto.payment.PaymentRequestDto;
import com.thekingmoss.application.dto.payment.PaymentResponseDto;
import com.thekingmoss.application.service.IPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final IPaymentService paymentService;

    @PostMapping("/create-intent")
    public ResponseEntity<PaymentResponseDto> crearPaymentIntent(@RequestBody PaymentRequestDto requestDto) {
        PaymentResponseDto responseDto = paymentService.createPaymentIntent(requestDto);
        return ResponseEntity.ok(responseDto);
    }
}
