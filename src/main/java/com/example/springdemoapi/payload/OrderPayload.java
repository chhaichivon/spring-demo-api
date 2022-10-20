package com.example.springdemoapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPayload {

    private String email;
    private String phoneNumber;
    private String phoneNumber2;
    private float amount;
    private String discountCode;
    private int discount;
    private float totalAmount;
    private boolean isDelivery;
    private Date deliveryDate;
    private List<Integer> productIds;
}
