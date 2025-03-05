package com.example.ivan.ruzhalovich.orders.entity;


import com.example.ivan.ruzhalovich.orders.models.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long costumerId;

    private OrderStatus status;

    private LocalDate date;

    private BigDecimal amount;

    public void updateStatus(OrderStatus status){
        this.status = status;
    }
}
