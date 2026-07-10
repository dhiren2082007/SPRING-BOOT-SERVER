package com.delivery.management.controller;

import com.delivery.management.dto.DeliveryAgentRequest;
import com.delivery.management.model.DeliveryAgent;
import com.delivery.management.service.DeliveryAgentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery-agents")
public class DeliveryAgentController {

    @Autowired
    private DeliveryAgentService deliveryAgentService;

    @PostMapping
    public ResponseEntity<DeliveryAgent> addAgent(@Valid @RequestBody DeliveryAgentRequest request) {
        DeliveryAgent saved = deliveryAgentService.addAgent(request);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public List<DeliveryAgent> getAllAgents() {
        return deliveryAgentService.getAllAgents();
    }

    @GetMapping("/{id}")
    public DeliveryAgent getAgentById(@PathVariable Long id) {
        return deliveryAgentService.getAgentById(id);
    }

    @PutMapping("/{id}")
    public DeliveryAgent updateAgent(@PathVariable Long id, @Valid @RequestBody DeliveryAgentRequest request) {
        return deliveryAgentService.updateAgent(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgent(@PathVariable Long id) {
        deliveryAgentService.deleteAgent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/available")
    public List<DeliveryAgent> getAvailableAgents() {
        return deliveryAgentService.getAvailableAgents();
    }

    @PatchMapping("/{id}/availability")
    public DeliveryAgent setAvailability(@PathVariable Long id, @RequestParam boolean available) {
        return deliveryAgentService.setAvailability(id, available);
    }
}
