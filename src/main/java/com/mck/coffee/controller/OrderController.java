package com.mck.coffee.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mck.coffee.DTO.OrderItemDTO;
import com.mck.coffee.model.Order;
import com.mck.coffee.repository.OrderRepository;
import com.mck.coffee.service.OrderService;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

   
    @Autowired
    private OrderService orderService;

    

    @GetMapping
    public ResponseEntity<List<Order>> getAll() {
        return ResponseEntity.ok(orderRepository.findAll());
    }

    @GetMapping("/id/{orderId}")
    public ResponseEntity<Order> getById(@PathVariable Long orderId) {
        return orderRepository.findById(orderId)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Order> post(@Valid @RequestBody OrderItemDTO orderItemDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.postOrderAndItem(orderItemDTO));
    }


}