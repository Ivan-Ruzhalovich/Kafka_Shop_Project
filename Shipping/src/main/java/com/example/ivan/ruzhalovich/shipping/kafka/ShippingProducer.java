package com.example.ivan.ruzhalovich.shipping.kafka;

import com.example.ivan.ruzhalovich.shipping.model.OrderModelNotification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShippingProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void sendNotification(OrderModelNotification orderModelNotification){
        try {
            kafkaTemplate.send("sent_orders",objectMapper.writeValueAsString(orderModelNotification));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
