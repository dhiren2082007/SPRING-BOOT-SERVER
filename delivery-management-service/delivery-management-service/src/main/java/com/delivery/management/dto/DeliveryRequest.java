package com.delivery.management.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryRequest {

    @NotNull
    private Long orderId;

    @NotNull
    private Long restaurantId;

    @NotNull
    private Long deliveryAgentId;

    private String deliveryAddress;
}
