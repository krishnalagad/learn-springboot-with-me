package com.learnspring.graphql2.controllers;

import com.learnspring.graphql2.entity.Order;
import com.learnspring.graphql2.entity.User;
import com.learnspring.graphql2.services.OrderService;
import com.learnspring.graphql2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @MutationMapping(name = "createOrder")
    public Order createOrder(
            @Argument String orderDetails, @Argument String address,
            @Argument Double price, @Argument Long userId
    ) {
        User user = this.userService.getUserById(userId);
        Order order = new Order();
        order.setOrderDetails(orderDetails);
        order.setAddress(address);
        order.setPrice(price);
        order.setUser(user);

        return orderService.createOrder(order);
    }

    @QueryMapping(name = "getOrder")
    public Order getOrderById(@Argument Long orderId) {
        return orderService.getOrderById(orderId);
    }

    @QueryMapping(name = "getOrders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    public Order updateOrder(Long orderId, Order order) {
        return orderService.updateOrder(orderId, order);
    }

    @MutationMapping(name = "deleteOrder")
    public void deleteOrder(@Argument Long orderId) {
        orderService.deleteOrder(orderId);
    }
}