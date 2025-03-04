package com.example.ivan.ruzhalovich.payment.service;

import com.example.ivan.ruzhalovich.payment.model.OrderModel;

public interface PaymentService {
    void payment(OrderModel orderModel);
}
