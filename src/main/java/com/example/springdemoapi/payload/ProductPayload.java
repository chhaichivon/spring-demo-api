package com.example.springdemoapi.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPayload {

    private String name;
    private String description;
    private float price;
    private int discount;
    private int categoryId;
}
