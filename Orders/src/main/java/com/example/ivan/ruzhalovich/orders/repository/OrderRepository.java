package com.example.ivan.ruzhalovich.orders.repository;

import com.example.ivan.ruzhalovich.orders.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
