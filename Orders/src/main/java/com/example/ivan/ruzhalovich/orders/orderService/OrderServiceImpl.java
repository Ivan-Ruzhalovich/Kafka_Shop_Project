package com.example.ivan.ruzhalovich.orders.orderService;

import com.example.ivan.ruzhalovich.orders.entity.Order;
import com.example.ivan.ruzhalovich.orders.models.OrderModel;
import com.example.ivan.ruzhalovich.orders.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository repository;
    private final KafkaTemplate<String,String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void letsDoIt(Order order) {
        try {
            String message = objectMapper.writeValueAsString(OrderModel.createOrderModelFromOrder(order));
            repository.save(order);
            kafkaTemplate.send("new_orders",message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Ошибка сериализации model в String");
        }
    }
}
