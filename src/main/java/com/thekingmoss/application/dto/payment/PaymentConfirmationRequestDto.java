package com.thekingmoss.application.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentConfirmationRequestDto {
    private Long pedidoId;
    private String stripePaymentId; // ID de transacción que Stripe confirmó

}
