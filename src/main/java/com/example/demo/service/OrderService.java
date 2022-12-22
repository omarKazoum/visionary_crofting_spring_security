package com.example.demo.service;


import com.example.demo.domain.Client;
import com.example.demo.domain.Order;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    OrderItemService orderItemService;
    public Order save(Order order){
        order.setUuid(UUID.randomUUID());
        order.setCreatedAt(LocalDateTime.now());
        order =  orderRepository.save(order);
        String userName=((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Client client= (Client) userDetailsService.getUserByEmail(userName).get();
        order.setClient(client);
        Order finalOrder = order;
        order.getOrderItems().forEach(orderItem -> {
            orderItem.setOrder(finalOrder);
            orderItemService.save(orderItem);
        });
        return order;
    }
    public List<Order> getAll(){
        return orderRepository.findAll();
    }
}
