package com.example.springdemoapi.service;

import com.example.springdemoapi.model.OrderEntity;
import com.example.springdemoapi.payload.OrderPayload;
import com.example.springdemoapi.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> findAll(Integer page, Integer limit){
        return orderRepository.findAll();
    }

    public OrderEntity findById(Integer orderId){
        return orderRepository.findById(orderId).orElse(null);
    }

    public OrderEntity save(OrderPayload orderPayload){
        return orderRepository.save(new OrderEntity());
    }

    public OrderEntity update(Integer orderId, OrderPayload orderPayload){
        return orderRepository.save(new OrderEntity());
    }

    public OrderEntity delete(Integer orderId){
        OrderEntity category = findById(orderId);
        if(Objects.nonNull(category))
            orderRepository.deleteById(orderId);
        return category;
    }
}
