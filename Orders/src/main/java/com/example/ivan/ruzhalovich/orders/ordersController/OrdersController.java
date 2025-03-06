package com.example.ivan.ruzhalovich.orders.ordersController;

import com.example.ivan.ruzhalovich.orders.entity.Order;
import com.example.ivan.ruzhalovich.orders.orderService.OrderServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrdersController {

    private final OrderServiceImpl orderService;

    @PostMapping("/new")
    public ResponseEntity<String> createNewOrder(@RequestBody Order order){
        orderService.letsDoIt(order);
        return new ResponseEntity<>("Its OK", HttpStatus.OK);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders(){
        return new ResponseEntity<>(orderService.getOrders(),HttpStatus.OK);
    }

}
