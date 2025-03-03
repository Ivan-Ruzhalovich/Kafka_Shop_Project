package com.example.ivan.ruzhalovich.orders.models;

import com.example.ivan.ruzhalovich.orders.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OrderModel {

    private Long id;

    private Long costumerId;

    private String status;

    private BigDecimal amount;

    public static OrderModel createOrderModelFromOrder(Order order){
        return new OrderModel(order.getId(),
                order.getCostumerId(),
                order.getStatus().getOrderStatus(),
                order.getAmount());
    }
}
