package com.example.springdemoapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_product")
public class ProductEntity implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    private float price;
    private int discount;
    @OneToMany(mappedBy = "product")
    private List<ProductImageEntity> productImages = new ArrayList<>();
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="category_id")
    private CategoryEntity category;
}
