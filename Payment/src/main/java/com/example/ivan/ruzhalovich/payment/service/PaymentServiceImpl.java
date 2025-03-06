package com.example.ivan.ruzhalovich.payment.service;

import com.example.ivan.ruzhalovich.payment.model.OrderModel;
import com.example.ivan.ruzhalovich.payment.model.OrderStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService{

    @Override
    @Transactional
    public void payment(OrderModel orderModel) {
        orderModel.updateStatus(OrderStatus.PAYMENT);
    }
}
