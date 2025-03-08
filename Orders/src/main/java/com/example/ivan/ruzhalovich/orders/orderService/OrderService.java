package com.example.ivan.ruzhalovich.orders.orderService;

import com.example.ivan.ruzhalovich.orders.entity.Order;
import com.example.ivan.ruzhalovich.orders.models.NotificationModel;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface OrderService {
    void letsDoIt (Order order) throws JsonProcessingException;
    void updateStatus(NotificationModel notification);
}
