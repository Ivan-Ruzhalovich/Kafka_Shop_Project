package com.example.ivan.ruzhalovich.shipping.kafka;

import com.example.ivan.ruzhalovich.shipping.model.OrderModel;
import com.example.ivan.ruzhalovich.shipping.model.NotificationModel;
import com.example.ivan.ruzhalovich.shipping.service.PackagingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShippingConsumer {

    private final ObjectMapper objectMapper;
    private final ShippingProducer shippingProducer;
    private final String topic = "payed_orders";
    private static final Logger log = LoggerFactory.getLogger(ShippingConsumer.class);
    PackagingService packagingService;

    @KafkaListener(topics = topic,groupId = "shipping_group",concurrency = "10")
    public void listenerOrdersForShipping(ConsumerRecord<String,String> order){
        try {
            OrderModel orderModel = objectMapper.readValue(order.value(),OrderModel.class);
            log.info("Message:{}was received",order.value());
            packagingService.packaging(orderModel);
            shippingProducer.sendNotification(NotificationModel.createFromOrder(orderModel));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
