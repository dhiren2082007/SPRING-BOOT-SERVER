package com.delivery.management.controller;

import com.delivery.management.dto.DeliveryRequest;
import com.delivery.management.model.Delivery;
import com.delivery.management.model.DeliveryStatus;
import com.delivery.management.service.DeliveryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @PostMapping
    public ResponseEntity<Delivery> assignDelivery(@Valid @RequestBody DeliveryRequest request) {
        Delivery created = deliveryService.assignDelivery(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Delivery getDeliveryById(@PathVariable Long id) {
        return deliveryService.getDeliveryById(id);
    }

    @GetMapping
    public List<Delivery> getAllDeliveries(@RequestParam(required = false) Long agentId) {
        if (agentId != null) {
            return deliveryService.getDeliveriesByAgent(agentId);
        }
        return deliveryService.getAllDeliveries();
    }

    @PatchMapping("/{id}/status")
    public Delivery updateStatus(@PathVariable Long id, @RequestParam DeliveryStatus status) {
        return deliveryService.updateStatus(id, status);
    }
}
