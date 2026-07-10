package com.swiggy.clone.service;

import com.swiggy.clone.dto.OrderRequest;
import com.swiggy.clone.model.Order;
import com.swiggy.clone.model.OrderStatus;

import java.util.List;

public interface OrderService {

    Order placeOrder(OrderRequest request);

    Order getOrderById(Long id);

    List<Order> getAllOrders();

    List<Order> getOrdersByUser(Long userId);

    Order updateOrderStatus(Long id, OrderStatus status);

    void cancelOrder(Long id);
}
