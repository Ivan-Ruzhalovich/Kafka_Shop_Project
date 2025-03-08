package com.example.ivan.ruzhalovich.notifications.kafka;

import com.example.ivan.ruzhalovich.notifications.model.NotificationModel;
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
public class NotificationProducer {

    private final KafkaTemplate<String,String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private static final Logger log = LoggerFactory.getLogger(NotificationProducer.class);
    private final NewTopic topic;
    private final String logsTopic = "Logs";

    public void sendStatusToDataBase(NotificationModel model){
        try {
            kafkaTemplate.send(topic.name(), objectMapper
                            .writeValueAsString(model))
                    .whenComplete((result, exception) -> {
                        if(exception==null)
                            log.info("Order status:{} was sent to {}",model.getStatus(),result.getProducerRecord().topic());
                        else log.error("Order status:{} was not sent, exception:{}",model.getStatus(),exception.getMessage());
                    });
            String logs = "Notificaton Producer: Order status: " + model.getStatus() +" -> ";
            kafkaTemplate.send(logsTopic,3,"NotificationKey",objectMapper.writeValueAsString(logs));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
