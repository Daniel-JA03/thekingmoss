package com.thekingmoss.application.service;

import com.thekingmoss.application.dto.payment.PaymentRequestDto;
import com.thekingmoss.application.dto.payment.PaymentResponseDto;

public interface IPaymentService {
    PaymentResponseDto createPaymentIntent(PaymentRequestDto request);
}
