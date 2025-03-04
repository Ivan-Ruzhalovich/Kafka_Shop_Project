package com.example.ivan.ruzhalovich.shipping.kafka;

import com.example.ivan.ruzhalovich.shipping.model.OrderModel;
import com.example.ivan.ruzhalovich.shipping.model.OrderModelNotification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShippingConsumer {

    private final ObjectMapper objectMapper;
    private final ShippingProducer shippingProducer;

    @KafkaListener(topics = "payed_orders",groupId = "shipping_group")
    public void listenerOrdersForShipping(ConsumerRecord<String,String> order){
        try {
            OrderModel orderModel = objectMapper.readValue(order.value(),OrderModel.class);
            shippingProducer.sendNotification(OrderModelNotification.createFromOrder(orderModel));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
