package com.example.demo.rest.controllers;



import com.example.demo.domain.Order;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrdersResource {

    @Autowired
    OrderService orderService;

    @PostMapping("/") @ResponseBody
    public Order save(@RequestBody @Valid Order order) {
        return orderService.save(order);
    }
    @GetMapping("/")
    public List<Order> getAll(){
       return orderService.getAll();
    }

}
