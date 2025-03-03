package com.example.ivan.ruzhalovich.orders.models;

public enum OrderStatus {
    NEW("Новый заказ"),
    AWAITING_PAYMENT("Заказ обработан и ожидает оплаты"),
    PAYMENT("Заказ оплачен"),
    PACKAGE("Заказ передан на упаковку"),
    DELIVERED("Заказ доставлен клиенту");

    private final String status;

    OrderStatus(String status){
        this.status = status;
    }

    public String getOrderStatus(){
        return status;
    }
}
