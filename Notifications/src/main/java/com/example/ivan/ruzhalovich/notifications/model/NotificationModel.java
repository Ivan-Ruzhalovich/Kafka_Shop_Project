package com.example.ivan.ruzhalovich.notifications.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Data
public class NotificationModel {

    private Long id;
    private OrderStatus status;

    public void updateStatus(OrderStatus status){
        this.status = status;
    }
}
