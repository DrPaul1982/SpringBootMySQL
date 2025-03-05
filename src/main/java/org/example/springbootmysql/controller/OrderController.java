package org.example.springbootmysql.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootmysql.model.CreateOrderRequest;
import org.example.springbootmysql.model.Order;
import org.example.springbootmysql.model.UpdateOrderRequest;
import org.example.springbootmysql.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    private ResponseEntity<Order> createOrder (@RequestBody CreateOrderRequest order) {
        return  ResponseEntity.ok(orderService.addOrder(order));
    }

    @PutMapping("/{id}")
    private ResponseEntity<Order> updateOrder (@PathVariable Long id, @RequestBody UpdateOrderRequest order) {
        return  ResponseEntity.ok(orderService.updateOrder(id, order));
    }

    @GetMapping("/{id}")
    private ResponseEntity<Order> getOrder (@PathVariable Long id) {
        return  ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping
    private ResponseEntity<List<Order>> getAllOrders () {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Order> deleteOrder (@PathVariable Long id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }
}
