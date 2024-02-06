package com.restaurant.coreservice.infraestructure.controller;

import com.restaurant.coreservice.aggregates.constant.HttpStatus;
import com.restaurant.coreservice.aggregates.request.RequestOrder;
import com.restaurant.coreservice.aggregates.response.ResponseBase;
import com.restaurant.coreservice.aggregates.response.ResponseSunat;
import com.restaurant.coreservice.application.service.OrderService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/core/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping()
     public ResponseBase createOrder(@RequestBody RequestOrder requestOrder){
         return orderService.createOrder(requestOrder);
     }
     @GetMapping("/{id}")
    public ResponseBase findOrder(@PathVariable Integer id){
        return orderService.findOrder(id);
     }
}
