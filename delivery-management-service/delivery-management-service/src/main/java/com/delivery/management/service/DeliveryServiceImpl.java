package com.delivery.management.service;

import com.delivery.management.dto.DeliveryRequest;
import com.delivery.management.exception.ResourceNotFoundException;
import com.delivery.management.model.Delivery;
import com.delivery.management.model.DeliveryAgent;
import com.delivery.management.model.DeliveryStatus;
import com.delivery.management.repository.DeliveryAgentRepository;
import com.delivery.management.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private DeliveryAgentRepository deliveryAgentRepository;

    @Autowired
    private ExternalRestaurantService externalRestaurantService;

    @Override
    @Transactional
    public Delivery assignDelivery(DeliveryRequest request) {
        // Validate the restaurant is active in restaurant-management-service (via RestTemplate)
        if (!externalRestaurantService.isRestaurantActive(request.getRestaurantId())) {
            throw new IllegalArgumentException(
                    "Restaurant " + request.getRestaurantId() + " is not active; cannot assign delivery");
        }

        DeliveryAgent agent = deliveryAgentRepository.findById(request.getDeliveryAgentId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Delivery agent not found with id: " + request.getDeliveryAgentId()));

        if (!Boolean.TRUE.equals(agent.getAvailable())) {
            throw new IllegalArgumentException("Delivery agent " + agent.getId() + " is not available");
        }

        Delivery delivery = Delivery.builder()
                .orderId(request.getOrderId())
                .restaurantId(request.getRestaurantId())
                .deliveryAgent(agent)
                .deliveryAddress(request.getDeliveryAddress())
                .status(DeliveryStatus.ASSIGNED)
                .build();

        agent.setAvailable(false);
        deliveryAgentRepository.save(agent);

        return deliveryRepository.save(delivery);
    }

    @Override
    public Delivery getDeliveryById(Long id) {
        return deliveryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery not found with id: " + id));
    }

    @Override
    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }

    @Override
    public List<Delivery> getDeliveriesByAgent(Long agentId) {
        return deliveryRepository.findByDeliveryAgentId(agentId);
    }

    @Override
    @Transactional
    public Delivery updateStatus(Long id, DeliveryStatus status) {
        Delivery delivery = getDeliveryById(id);
        delivery.setStatus(status);

        if (status == DeliveryStatus.DELIVERED || status == DeliveryStatus.FAILED) {
            DeliveryAgent agent = delivery.getDeliveryAgent();
            agent.setAvailable(true);
            deliveryAgentRepository.save(agent);
        }

        return deliveryRepository.save(delivery);
    }
}
