package com.thekingmoss.application.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequestDto {
    private Long pedidoId;
    private String email;
    private Long amount;     // en centavos (5000 = S/ 50.00)
    private String currency; // "usd", "eur", "pen"
}
