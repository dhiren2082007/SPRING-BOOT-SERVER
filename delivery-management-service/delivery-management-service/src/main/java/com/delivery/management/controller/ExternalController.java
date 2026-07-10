package com.delivery.management.controller;

import com.delivery.management.service.ExternalRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/external")
public class ExternalController {

    @Autowired
    private ExternalRestaurantService externalRestaurantService;

    @GetMapping("/restaurants/{restaurantId}/active")
    public boolean isRestaurantActive(@PathVariable Long restaurantId) {
        return externalRestaurantService.isRestaurantActive(restaurantId);
    }
}
