package com.example.ivan.ruzhalovich.shipping.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderModel {

    private Long id;

    private Long costumerId;

    private OrderStatus status;

    private BigDecimal amount;

    public void updateStatus(OrderStatus status){
        this.status = status;
    }
}
