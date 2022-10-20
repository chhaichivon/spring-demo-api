package com.example.springdemoapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryPayload {

    private String name;
    private String description;
    private Integer parentId;
}
