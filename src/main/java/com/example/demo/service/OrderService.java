package com.example.demo.service;


import com.example.demo.domain.Order;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemService orderItemService;
    public Order save(Order order){
        order.setUuid(UUID.randomUUID());
        order.setCreatedAt(LocalDateTime.now());
        order =  orderRepository.save(order);
        Order finalOrder = order;
        order.getOrderItems().forEach(orderItem -> {
            orderItem.setOrder(finalOrder);
            orderItemService.save(orderItem);
        });
        return order;

    }
    // get all orders
    public Iterable<Order> findAll() {
        return orderRepository.findAll();
    }
}
