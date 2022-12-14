package com.example.springdemoapi.service;

import com.example.springdemoapi.model.EStatus;
import com.example.springdemoapi.model.OrderDetailEntity;
import com.example.springdemoapi.model.OrderEntity;
import com.example.springdemoapi.payload.OrderPayload;
import com.example.springdemoapi.repository.OrderDetailRepository;
import com.example.springdemoapi.repository.OrderRepository;
import com.example.springdemoapi.repository.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    private final OrderDetailRepository orderDetailRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, OrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public List<OrderEntity> findAll(Integer page, Integer limit){
        return orderRepository.findAll(PageRequest.of(page,limit)).getContent();
    }

    public OrderEntity findById(Integer orderId){
        return orderRepository.findById(orderId).orElse(null);
    }

    public OrderEntity save(OrderPayload orderPayload){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setEmail(orderPayload.getEmail());
        orderEntity.setPhoneNumber(orderPayload.getPhoneNumber());
        orderEntity.setPhoneNumber2(orderPayload.getPhoneNumber2());
        orderEntity.setAmount(orderPayload.getAmount());
        orderEntity.setDiscountCode(orderPayload.getDiscountCode());
        orderEntity.setDiscount(orderPayload.getDiscount());
        orderEntity.setTotalAmount(orderPayload.getTotalAmount());
        orderEntity.setDelivery(orderPayload.isDelivery());
        orderEntity.setDeliveryDate(orderPayload.getDeliveryDate());
        orderEntity.setStatus(EStatus.ACTIVE);
        orderEntity.setCreatedAt(new Date());
        orderEntity = orderRepository.save(orderEntity);
        List<OrderDetailEntity> orderDetailEntities = new ArrayList<>();
        for (Integer productId: orderPayload.getProductIds()){
            OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
            orderDetailEntity.setOrder(orderEntity);
            orderDetailEntity.setProduct(productRepository.findById(productId).orElse(null));
            orderDetailEntities.add(orderDetailEntity);
        }
        orderDetailRepository.saveAll(orderDetailEntities);
        return orderEntity;
    }

    public OrderEntity update(Integer orderId, OrderPayload orderPayload){
        OrderEntity orderEntity = findById(orderId);
        if(Objects.nonNull(orderEntity)){
            orderEntity.setEmail(orderPayload.getEmail());
            orderEntity.setPhoneNumber(orderPayload.getPhoneNumber());
            orderEntity.setPhoneNumber2(orderPayload.getPhoneNumber2());
            orderEntity.setAmount(orderPayload.getAmount());
            orderEntity.setDiscountCode(orderPayload.getDiscountCode());
            orderEntity.setDiscount(orderPayload.getDiscount());
            orderEntity.setTotalAmount(orderPayload.getTotalAmount());
            orderEntity.setDelivery(orderPayload.isDelivery());
            orderEntity.setDeliveryDate(orderPayload.getDeliveryDate());
            orderEntity.setStatus(EStatus.ACTIVE);
            orderEntity.setUpdatedAt(new Date());
            orderEntity = orderRepository.save(orderEntity);
        }
        return orderEntity;
    }

    public OrderEntity delete(Integer orderId){
        OrderEntity orderEntity = findById(orderId);
        if(Objects.nonNull(orderEntity))
            orderRepository.deleteById(orderId);
        return orderEntity;
    }
}
