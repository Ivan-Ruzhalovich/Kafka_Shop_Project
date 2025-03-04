package com.example.ivan.ruzhalovich.shipping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Data
public class OrderModelNotification {

    private Long id;

    public static OrderModelNotification createFromOrder(OrderModel orderModel){
        return new OrderModelNotification(orderModel.getId());
    }

}