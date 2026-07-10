package com.delivery.management.service;

import com.delivery.management.dto.DeliveryRequest;
import com.delivery.management.model.Delivery;
import com.delivery.management.model.DeliveryStatus;

import java.util.List;

public interface DeliveryService {
    Delivery assignDelivery(DeliveryRequest request);
    Delivery getDeliveryById(Long id);
    List<Delivery> getAllDeliveries();
    List<Delivery> getDeliveriesByAgent(Long agentId);
    Delivery updateStatus(Long id, DeliveryStatus status);
}
