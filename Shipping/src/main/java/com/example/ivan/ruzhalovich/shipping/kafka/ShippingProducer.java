package com.example.ivan.ruzhalovich.shipping.kafka;

import com.example.ivan.ruzhalovich.shipping.model.OrderModelNotification;
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

    public void sendNotification(OrderModelNotification orderModelNotification) {
        try {
            kafkaTemplate.send(topic.name(), objectMapper
                            .writeValueAsString(orderModelNotification))
                    .whenComplete((result, exception) -> {
                        if (exception == null)
                            log.info("Message id:{} was sent to{}", orderModelNotification.getId(), result.getProducerRecord().topic());
                        else
                            log.error("Message id:{} was not sent, exception:{}", orderModelNotification.getId(), exception.getMessage());
                    });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
