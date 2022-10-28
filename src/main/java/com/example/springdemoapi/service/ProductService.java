package com.example.springdemoapi.service;

import com.example.springdemoapi.model.CategoryEntity;
import com.example.springdemoapi.model.ProductEntity;
import com.example.springdemoapi.model.ProductImageEntity;
import com.example.springdemoapi.payload.ProductImagePayload;
import com.example.springdemoapi.payload.ProductPayload;
import com.example.springdemoapi.repository.CategoryRepository;
import com.example.springdemoapi.repository.ProductImageRepository;
import com.example.springdemoapi.repository.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductImageRepository productImageRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ProductImageRepository productImageRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productImageRepository = productImageRepository;
    }

    public List<ProductEntity> findAll(Integer page, Integer size){
        return productRepository.findAll(PageRequest.of(page,size)).getContent();
    }

    public ProductEntity findById(Integer productId){
        return productRepository.findById(productId).orElse(null);
    }

    public ProductEntity save(ProductPayload productPayload){
        CategoryEntity categoryEntity = categoryRepository.findById(productPayload.getCategoryId()).orElse(null);
        if(Objects.nonNull(categoryEntity)){
            ProductEntity productEntity = new ProductEntity();
            productEntity.setName(productPayload.getName());
            productEntity.setDescription(productPayload.getDescription());
            productEntity.setPrice(productPayload.getPrice());
            productEntity.setDiscount(productPayload.getDiscount());
            productEntity.setCategory(categoryEntity);
            productEntity = productRepository.save(productEntity);
            for (ProductImagePayload productImage : productPayload.getProductImages()){
                ProductImageEntity productImageEntity = new ProductImageEntity();
                productImageEntity.setImageUrl(productImage.getImageUrl());
                productImageEntity.setThumbnail(productImage.getThumbnail());
                productImageEntity.setProduct(productEntity);
                productImageRepository.save(productImageEntity);
            }
            return productEntity;
        }
        return null;
    }

    public ProductEntity update(Integer productId, ProductPayload productPayload){
        ProductEntity productEntity = findById(productId);
        if(Objects.nonNull(productPayload) && Objects.nonNull(productEntity)){
            return productRepository.save(productEntity);
        }
        return null;
    }

    public ProductEntity delete(Integer productId){
        ProductEntity productEntity = findById(productId);
        if(Objects.nonNull(productEntity)){
            productRepository.deleteById(productId);
        }
        return null;
    }
}
