package com.example.ivan.ruzhalovich.orders.entity;


import com.example.ivan.ruzhalovich.orders.models.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long costumerId;

    private OrderStatus status;

    private LocalDateTime date = LocalDateTime.now();

    private BigDecimal amount;

    public void updateStatus(OrderStatus status){
        this.status = status;
    }
}
