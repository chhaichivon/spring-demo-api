package com.example.springdemoapi.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPayload implements Serializable{

    private String name;
    private String description;
    private float price;
    private int discount;
    private int categoryId;
    private List<ProductImagePayload> productImages;

}
