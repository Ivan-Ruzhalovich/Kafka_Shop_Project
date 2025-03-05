package com.example.ivan.ruzhalovich.orders.orderService;

import com.example.ivan.ruzhalovich.orders.entity.Order;

public interface OrderService {
    void letsDoIt (Order order);
    void updateStatus(Long id);
}
