package com.example.ivan.ruzhalovich.shipping.kafka;

import com.example.ivan.ruzhalovich.shipping.model.NotificationModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShippingProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final NewTopic topic;
    private static final Logger log = LoggerFactory.getLogger(ShippingProducer.class);
    private final String feetBackTopic = "feetBack";

    public void sendNotification(NotificationModel notificationModel) {
        try {
            String notificationString = objectMapper.writeValueAsString(notificationModel);
            kafkaTemplate.send(topic.name(), notificationString)
                    .whenComplete((result, exception) -> {
                        if (exception == null)
                            log.info("Message id:{} was sent to {}", notificationModel.getId(), result.getProducerRecord().topic());
                        else
                            log.error("Message id:{} was not sent, exception:{}", notificationModel.getId(), exception.getMessage());
                    });
            kafkaTemplate.send(feetBackTopic, notificationString)
                    .whenComplete((result, exception) -> {
                        if (exception == null)
                            log.info("Order Status{} was sent to{}", notificationModel.getStatus(), result.getProducerRecord().topic());
                        else
                            log.error("Order Status{} was not sent, exception:{}", notificationModel.getStatus(), exception.getMessage());
                    });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
