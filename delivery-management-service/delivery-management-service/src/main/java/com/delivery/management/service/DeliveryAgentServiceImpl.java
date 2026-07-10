package com.delivery.management.service;

import com.delivery.management.dto.DeliveryAgentRequest;
import com.delivery.management.exception.ResourceNotFoundException;
import com.delivery.management.model.DeliveryAgent;
import com.delivery.management.repository.DeliveryAgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryAgentServiceImpl implements DeliveryAgentService {

    @Autowired
    private DeliveryAgentRepository deliveryAgentRepository;

    @Override
    public DeliveryAgent addAgent(DeliveryAgentRequest request) {
        DeliveryAgent agent = DeliveryAgent.builder()
                .name(request.getName())
                .phone(request.getPhone())
                .vehicleNumber(request.getVehicleNumber())
                .available(true)
                .build();
        return deliveryAgentRepository.save(agent);
    }

    @Override
    public List<DeliveryAgent> getAllAgents() {
        return deliveryAgentRepository.findAll();
    }

    @Override
    public DeliveryAgent getAgentById(Long id) {
        return deliveryAgentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery agent not found with id: " + id));
    }

    @Override
    public DeliveryAgent updateAgent(Long id, DeliveryAgentRequest request) {
        DeliveryAgent agent = getAgentById(id);
        agent.setName(request.getName());
        agent.setPhone(request.getPhone());
        agent.setVehicleNumber(request.getVehicleNumber());
        return deliveryAgentRepository.save(agent);
    }

    @Override
    public void deleteAgent(Long id) {
        DeliveryAgent agent = getAgentById(id);
        deliveryAgentRepository.delete(agent);
    }

    @Override
    public List<DeliveryAgent> getAvailableAgents() {
        return deliveryAgentRepository.findByAvailableTrue();
    }

    @Override
    public DeliveryAgent setAvailability(Long id, boolean available) {
        DeliveryAgent agent = getAgentById(id);
        agent.setAvailable(available);
        return deliveryAgentRepository.save(agent);
    }
}
