package org.example.springbootmysql.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootmysql.model.CreateOrderRequest;
import org.example.springbootmysql.model.Order;
import org.example.springbootmysql.model.Product;
import org.example.springbootmysql.model.UpdateOrderRequest;
import org.example.springbootmysql.repository.OrderRepository;
import org.example.springbootmysql.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;


    public Order addOrder(CreateOrderRequest orderRequest) {
        List<Product> products = orderRequest.getProductIds().stream()
                .map(product -> productRepository.findById(product)
                        .orElseThrow(() -> new RuntimeException("Product not found: " + product)))
                .collect(Collectors.toList());
        Order order = new Order();
        order.setProducts(products);

        double totalCost = products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
        order.setTotalCost(totalCost);

        order.setCreatedAt(LocalDateTime.now());

        return orderRepository.save(order);
    }


    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }

    public Order updateOrder(Long id, UpdateOrderRequest orderRequest) {
        Order order = getOrderById(id);

        List<Product> products = orderRequest.getProductIds().stream()
                .map(product -> productRepository.findById(product)
                        .orElseThrow(() -> new RuntimeException("Product not found: " + product)))
                .collect(Collectors.toList());

        order.setProducts(products);

        double totalCost = products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
        order.setTotalCost(totalCost);

        return orderRepository.save(order);
    }

}
