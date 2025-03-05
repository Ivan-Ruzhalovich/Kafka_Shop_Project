package com.example.ivan.ruzhalovich.notifications.kafka;

import com.example.ivan.ruzhalovich.notifications.model.NotificationModel;
import com.example.ivan.ruzhalovich.notifications.service.NotificationService;
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
public class NotificationConsumer {

    private final ObjectMapper objectMapper;
    private final NotificationService notificationService;
    private final String topic = "sent_orders";
    private static final Logger log = LoggerFactory.getLogger(NotificationConsumer.class);
    private final NotificationProducer producer;

    @KafkaListener(topics = topic, groupId = "notifications_group")
    public void newNotification(ConsumerRecord<String, String> notification) {
//        notificationService.sendNotification(getMessage(notification.value()));
        try {
            NotificationModel model = objectMapper.readValue(notification.value(), NotificationModel.class);
            producer.sendStatusToDataBase(model);
            log.info("Message id:{} was received", model.getId());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

