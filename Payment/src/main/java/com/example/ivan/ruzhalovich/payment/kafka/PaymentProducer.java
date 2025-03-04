package com.example.ivan.ruzhalovich.payment.kafka;

import com.example.ivan.ruzhalovich.payment.model.OrderModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentProducer {

    private final KafkaTemplate<String,String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void sendOrder(OrderModel model){
        try {
            kafkaTemplate.send("payed_orders",objectMapper.writeValueAsString(model));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
