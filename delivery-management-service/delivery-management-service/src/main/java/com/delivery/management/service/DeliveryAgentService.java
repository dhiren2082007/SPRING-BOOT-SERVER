package com.delivery.management.service;

import com.delivery.management.dto.DeliveryAgentRequest;
import com.delivery.management.model.DeliveryAgent;

import java.util.List;

public interface DeliveryAgentService {
    DeliveryAgent addAgent(DeliveryAgentRequest request);
    List<DeliveryAgent> getAllAgents();
    DeliveryAgent getAgentById(Long id);
    DeliveryAgent updateAgent(Long id, DeliveryAgentRequest request);
    void deleteAgent(Long id);
    List<DeliveryAgent> getAvailableAgents();
    DeliveryAgent setAvailability(Long id, boolean available);
}
