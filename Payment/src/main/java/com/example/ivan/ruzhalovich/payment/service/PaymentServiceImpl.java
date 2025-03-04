package com.example.ivan.ruzhalovich.payment.service;

import com.example.ivan.ruzhalovich.payment.model.OrderModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PaymentServiceImpl implements PaymentService{

    @Override
    @Transactional
    public void payment(OrderModel orderModel) {
        //оплачиваем и обновляем статус в БД
    }
}
