package com.example.springdemoapi.controller;

import com.example.springdemoapi.model.OrderEntity;
import com.example.springdemoapi.payload.OrderPayload;
import com.example.springdemoapi.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderEntity>> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                                     @RequestParam(value = "limit", defaultValue = "30") int limit) {
        if(limit > 30){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(orderService.findAll(page,limit), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "{orderId}", method = RequestMethod.GET)
    public ResponseEntity<OrderEntity> findById(@PathVariable("orderId") Integer orderId) {
        if(orderId == 0 || orderId < 0){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(orderService.findById(orderId), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<OrderEntity> save(@RequestBody OrderPayload orderPayload) {
        if(Objects.isNull(orderPayload)){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(orderService.save(orderPayload), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{orderId}", method = RequestMethod.PUT)
    public ResponseEntity<OrderEntity> update(@PathVariable("orderId") Integer orderId,
                                           @RequestBody OrderPayload orderPayload) {
        if(orderId == 0 || orderId < 0){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(orderService.update(orderId, orderPayload), HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/{orderId}")
    public ResponseEntity<OrderEntity> delete(@PathVariable("orderId") Integer orderId){
        if(orderId == 0 || orderId < 0){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(orderService.delete(orderId), HttpStatus.OK);
        }
    }
}
