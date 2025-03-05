package com.example.ivan.ruzhalovich.shipping.service;

import com.example.ivan.ruzhalovich.shipping.model.OrderModel;
import com.example.ivan.ruzhalovich.shipping.model.OrderStatus;
import org.springframework.stereotype.Service;

@Service
public class PackagingServiceImpl implements PackagingService{
    @Override
    public void packaging(OrderModel orderModel) {
        orderModel.updateStatus(OrderStatus.PACKAGE);
    }
}
