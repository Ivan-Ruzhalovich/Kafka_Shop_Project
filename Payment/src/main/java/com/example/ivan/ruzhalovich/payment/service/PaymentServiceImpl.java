package com.example.ivan.ruzhalovich.payment.service;

import com.example.ivan.ruzhalovich.payment.model.OrderModel;
import com.example.ivan.ruzhalovich.payment.model.OrderStatus;
import com.example.ivan.ruzhalovich.payment.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository repository;

    @Override
    @Transactional
    public void payment(OrderModel orderModel) {
        orderModel.updateStatus(OrderStatus.PAYMENT);
//        repository.save(orderModel);
    }
}
