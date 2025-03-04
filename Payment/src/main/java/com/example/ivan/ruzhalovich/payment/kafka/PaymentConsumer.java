package com.example.ivan.ruzhalovich.payment.kafka;

import com.example.ivan.ruzhalovich.payment.model.OrderModel;
import com.example.ivan.ruzhalovich.payment.service.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentConsumer {

    private final ObjectMapper objectMapper;
    private final PaymentService paymentService;
    private final PaymentProducer paymentProducer;

    @KafkaListener(topics = "new_orders",groupId = "create_new_order")
    public void listenerOrdersForPayment(ConsumerRecord<String,String> message){
        try {
            OrderModel orderModel = objectMapper.readValue(message.value(), OrderModel.class);
            System.out.println(orderModel);
            paymentService.payment(orderModel);
            paymentProducer.sendOrder(orderModel);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
