package com.learnspring.graphql2.controllers;

import com.learnspring.graphql2.entity.Order;
import com.learnspring.graphql2.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    public Order createOrder(Order order) {
        return orderService.createOrder(order);
    }

    public Order getOrderById(Long orderId) {
        return orderService.getOrderById(orderId);
    }

    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    public Order updateOrder(Long orderId, Order order) {
        return orderService.updateOrder(orderId, order);
    }

    public void deleteOrder(Long orderId) {
        orderService.deleteOrder(orderId);
    }
}