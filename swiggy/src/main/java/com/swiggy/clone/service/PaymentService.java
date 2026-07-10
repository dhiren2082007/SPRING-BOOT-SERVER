package com.swiggy.clone.service;

import com.swiggy.clone.dto.PaymentRequest;
import com.swiggy.clone.model.Payment;
import com.swiggy.clone.model.PaymentStatus;

import java.util.List;

public interface PaymentService {

    Payment createPayment(PaymentRequest request);

    Payment getPaymentById(Long id);

    Payment getPaymentByOrderId(Long orderId);

    List<Payment> getAllPayments();

    Payment updatePaymentStatus(Long id, PaymentStatus status);
}
