package com.example.ivan.ruzhalovich.notifications.kafka;

import com.example.ivan.ruzhalovich.notifications.model.NotificationModel;
import com.example.ivan.ruzhalovich.notifications.service.NotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationConsumer {

    private final ObjectMapper objectMapper;
    private final NotificationService notificationService;

    @KafkaListener(topics = "sent_orders",groupId = "notifications_group")
    public void newNotification(ConsumerRecord<String,String> notification){
        notificationService.sendNotification(getMessage(notification.value()));
    }

    private String getMessage(String string){
        try {
            Long id = objectMapper.readValue(string, NotificationModel.class).getId();
            return "Order № " + id + " was delivered!";
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
