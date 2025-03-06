package com.example.ivan.ruzhalovich.orders.kafka;

import com.example.ivan.ruzhalovich.orders.models.NotificationModel;
import com.example.ivan.ruzhalovich.orders.orderService.OrderService;
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
public class NotificationsConsumer {

    private final ObjectMapper objectMapper;
    private final OrderService orderService;
    private final Logger log = LoggerFactory.getLogger(NotificationsConsumer.class);
    private final String topic = "feetBack";

    @KafkaListener(topics = topic,groupId = "create_new_order",concurrency = "10")
    public void listenerOrdersForPayment(ConsumerRecord<String,String> message){
        try {
            NotificationModel notification = objectMapper.readValue(message.value(), NotificationModel.class);
            log.info("Message:{}was received",message.value());
            orderService.updateStatus(notification);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
