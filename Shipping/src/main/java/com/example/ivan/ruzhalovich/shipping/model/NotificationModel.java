package com.example.ivan.ruzhalovich.shipping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Data
public class NotificationModel {

    private Long id;
    private OrderStatus status;

    public static NotificationModel createFromOrder(OrderModel orderModel){
        return new NotificationModel(orderModel.getId(),orderModel.getStatus());
    }

    public void updateStatus(OrderStatus status){
        this.status = status;
    }
}