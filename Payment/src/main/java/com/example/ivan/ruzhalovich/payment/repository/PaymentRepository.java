package com.example.ivan.ruzhalovich.payment.repository;

import com.example.ivan.ruzhalovich.payment.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<OrderModel,Long> {

}
