package com.example.ivan.ruzhalovich.shipping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Data
public class OrderModel {

    private Long id;

    private Long costumerId;

    private String status;

    private BigDecimal amount;



}
