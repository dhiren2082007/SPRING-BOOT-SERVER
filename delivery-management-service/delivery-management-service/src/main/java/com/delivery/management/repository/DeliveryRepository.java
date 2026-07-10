package com.delivery.management.repository;

import com.delivery.management.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    List<Delivery> findByDeliveryAgentId(Long deliveryAgentId);
    Optional<Delivery> findByOrderId(Long orderId);
}
