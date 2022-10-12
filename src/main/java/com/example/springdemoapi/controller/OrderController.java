package com.example.springdemoapi.controller;

import com.example.springdemoapi.model.OrderEntity;
import com.example.springdemoapi.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/v1/order")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public ResponseEntity<List<OrderEntity>> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                                     @RequestParam(value = "limit", defaultValue = "30") int limit) {
        if(limit > 30){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(orderRepository.findAll(), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "{orderId}", method = RequestMethod.GET)
    public ResponseEntity<OrderEntity> findById(@PathVariable("orderId") Integer orderId) {
        if(orderId == 0 || orderId < 0){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(orderRepository.findById(orderId).orElse(null), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<OrderEntity> save(@RequestBody OrderEntity orderEntity) {
        if(Objects.isNull(orderEntity)){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(orderRepository.save(orderEntity), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{orderId}", method = RequestMethod.PUT)
    public ResponseEntity<OrderEntity> update(@PathVariable("orderId") Integer orderId,
                                           @RequestBody OrderEntity orderEntity) {
        if(orderId == 0 || orderId < 0){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            OrderEntity order = orderRepository.findById(orderId).orElse(null);
            if(Objects.nonNull(order)){
                orderEntity.setId(orderId);
                return new ResponseEntity<>(orderRepository.save(orderEntity), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }
    }

    @DeleteMapping(value = "/{orderId}")
    public ResponseEntity<OrderEntity> delete(@PathVariable("orderId") Integer orderId){
        if(orderId == 0 || orderId < 0){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            OrderEntity category = orderRepository.findById(orderId).orElse(null);
            if(Objects.nonNull(category)){
                orderRepository.deleteById(orderId);
                return new ResponseEntity<>(category, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }
    }
}
