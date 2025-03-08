package com.example.ivan.ruzhalovich.orders.orderService;

import com.example.ivan.ruzhalovich.orders.entity.Order;
import com.example.ivan.ruzhalovich.orders.models.NotificationModel;
import com.example.ivan.ruzhalovich.orders.models.OrderModel;
import com.example.ivan.ruzhalovich.orders.models.OrderStatus;
import com.example.ivan.ruzhalovich.orders.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository repository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
    private final NewTopic topic;
    private final String logsTopic = "Logs";

    @Override
    public void letsDoIt(Order order) throws JsonProcessingException {
            order.updateStatus(OrderStatus.AWAITING_PAYMENT);
            repository.save(order);
            String message = objectMapper.writeValueAsString(order.createOrderModelFromOrder());
            kafkaTemplate.send(topic.name(),message).whenComplete((result,exception) ->
            {
                if (exception==null)
                    log.info("Message id:{} was sent, offset",order.getId(),result.getRecordMetadata().offset());
                else log.error("Message id:{} was not sent",order.getId(),exception.getMessage());

            });
            String logs = "Orders Producer: Order status: " + order.getStatus() +" -> ";
            kafkaTemplate.send(logsTopic,0,"OrdersKey",logs);
    }

    @Override
    public void updateStatus(NotificationModel notification) {
        Order order = repository
                .findById(notification.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Такого заказа не существует!"));
        order.setStatus(notification.getStatus());
        log.info("Обновляем статус заказа №{}, новый статус - {}",order.getId(),order.getStatus());
        repository.save(order);
    }

    public List<Order> getOrders(){
        return repository.findAll();
    }
}
