package com.swiggy.clone.service;

import com.swiggy.clone.dto.OrderItemRequest;
import com.swiggy.clone.dto.OrderRequest;
import com.swiggy.clone.exception.ResourceNotFoundException;
import com.swiggy.clone.model.*;
import com.swiggy.clone.repository.ItemRepository;
import com.swiggy.clone.repository.OrderRepository;
import com.swiggy.clone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    @Override
    @Transactional
    public Order placeOrder(OrderRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with id: " + request.getUserId()));

        Order order = Order.builder()
                .user(user)
                .deliveryAddress(request.getDeliveryAddress() != null
                        ? request.getDeliveryAddress()
                        : user.getAddress())
                .status(OrderStatus.PLACED)
                .build();

        List<OrderItem> orderItems = new ArrayList<>();
        double totalAmount = 0.0;

        for (OrderItemRequest itemRequest : request.getItems()) {
            Item item = itemRepository.findById(itemRequest.getItemId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Item not found with id: " + itemRequest.getItemId()));

            if (!Boolean.TRUE.equals(item.getAvailable())) {
                throw new IllegalArgumentException("Item " + item.getId() + " is not available");
            }

            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .item(item)
                    .quantity(itemRequest.getQuantity())
                    .price(item.getPrice() * itemRequest.getQuantity())
                    .build();

            totalAmount += orderItem.getPrice();
            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);
        order.setTotalAmount(totalAmount);

        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    @Transactional
    public Order updateOrderStatus(Long id, OrderStatus status) {
        Order order = getOrderById(id);
        order.setStatus(status);
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public void cancelOrder(Long id) {
        Order order = getOrderById(id);
        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
    }
}
