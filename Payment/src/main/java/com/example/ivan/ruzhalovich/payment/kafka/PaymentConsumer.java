package com.example.ivan.ruzhalovich.payment.kafka;

import com.example.ivan.ruzhalovich.payment.model.NotificationModel;
import com.example.ivan.ruzhalovich.payment.model.OrderModel;
import com.example.ivan.ruzhalovich.payment.service.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentConsumer {

    private final ObjectMapper objectMapper;
    private final PaymentService paymentService;
    private final PaymentProducer paymentProducer;
    private final Logger log = LoggerFactory.getLogger(PaymentConsumer.class);
    private final String topic = "new_orders";

    @Retryable(retryFor = {Exception.class}, maxAttempts = 5,backoff = @Backoff(delay = 1000))
    @KafkaListener(topics = topic,groupId = "create_new_order",concurrency = "10")
    public void listenerOrdersForPayment(ConsumerRecord<String,String> message) throws JsonProcessingException {
            OrderModel orderModel = objectMapper.readValue(message.value(),OrderModel.class);
            log.info("Message:{}was received",message.value());
            paymentService.payment(orderModel);
            paymentProducer.sendOrder(orderModel);
            paymentProducer.sendNotification(NotificationModel.createFromOrder(orderModel));
    }
}
