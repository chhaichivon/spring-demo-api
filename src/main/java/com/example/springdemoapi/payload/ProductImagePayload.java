package com.example.springdemoapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductImagePayload implements Serializable {
    private String imageUrl;
    private String thumbnail;
}