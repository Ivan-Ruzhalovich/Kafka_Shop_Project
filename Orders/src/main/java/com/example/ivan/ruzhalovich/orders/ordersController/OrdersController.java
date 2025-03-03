package com.example.ivan.ruzhalovich.orders.ordersController;

import com.example.ivan.ruzhalovich.orders.entity.Order;
import com.example.ivan.ruzhalovich.orders.orderService.OrderServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrdersController {

    private final OrderServiceImpl orderService;

    @PostMapping("/new")
    public ResponseEntity<String> createNewOrder(@RequestBody Order order){
        orderService.doThis(order);
        return new ResponseEntity<>("Its OK", HttpStatus.OK);
    }
}
