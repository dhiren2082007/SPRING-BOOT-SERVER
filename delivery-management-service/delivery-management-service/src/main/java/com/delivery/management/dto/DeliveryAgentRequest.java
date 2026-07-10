package com.delivery.management.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryAgentRequest {

    @NotBlank(message = "Agent name is required")
    private String name;

    private String phone;

    private String vehicleNumber;
}
