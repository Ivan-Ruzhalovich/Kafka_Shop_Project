package com.example.ivan.ruzhalovich.orders.orderService;

import com.example.ivan.ruzhalovich.orders.entity.Order;
import com.example.ivan.ruzhalovich.orders.models.NotificationModel;

public interface OrderService {
    void letsDoIt (Order order);
    void updateStatus(NotificationModel notification);
}
