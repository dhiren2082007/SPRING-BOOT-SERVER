package com.swiggy.clone.dto;

import com.swiggy.clone.model.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {

    @NotNull
    private Long orderId;

    @NotNull
    @Positive
    private Double amount;

    @NotNull
    private PaymentMethod method;
}
