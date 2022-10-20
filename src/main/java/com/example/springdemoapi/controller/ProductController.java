package com.example.springdemoapi.controller;

import com.example.springdemoapi.model.CategoryEntity;
import com.example.springdemoapi.model.ProductEntity;
import com.example.springdemoapi.payload.ProductPayload;
import com.example.springdemoapi.repository.CategoryRepository;
import com.example.springdemoapi.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/v1/product")
public class ProductController {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductController(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @GetMapping
    public ResponseEntity<List<ProductEntity>> findAll(@RequestHeader Map<String, String> headers,
                                                       @RequestParam(value = "page", defaultValue = "0") int page,
                                                       @RequestParam(value = "limit", defaultValue = "30") int limit) {
        if(limit > 30){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "{productId}", method = RequestMethod.GET)
    public ResponseEntity<ProductEntity> findById(@RequestHeader Map<String, String> headers,
                                                  @PathVariable("productId") Integer productId) {
        if(productId == 0 || productId < 0){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(productRepository.findById(productId).orElse(null), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<ProductEntity> save(@RequestHeader Map<String, String> headers,
                                              @RequestBody ProductPayload productReq) {
        CategoryEntity categoryEntity = categoryRepository.findById(productReq.getCategoryId()).orElse(null);
        if(Objects.nonNull(categoryEntity)){
            ProductEntity productEntity = new ProductEntity();
            productEntity.setName(productReq.getName());
            productEntity.setDescription(productReq.getDescription());
            productEntity.setPrice(productReq.getPrice());
            productEntity.setDiscount(productReq.getDiscount());
            productEntity.setCategory(categoryEntity);
            return new ResponseEntity<>(productRepository.save(productEntity), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.PUT)
    public ResponseEntity<ProductEntity> update(@RequestHeader Map<String, String> headers,
                                                @PathVariable("productId") Integer productId,
                                                @RequestBody ProductEntity productEntity) {
        if(productId == 0 || productId < 0){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            ProductEntity pro = productRepository.findById(productId).orElse(null);
            if(Objects.nonNull(pro)){
                productEntity.setId(productId);
                return new ResponseEntity<>(productRepository.save(productEntity), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }
    }

    @DeleteMapping(value = "/{productId}")
    public ResponseEntity<ProductEntity> delete(@RequestHeader Map<String, String> headers,
                                                @PathVariable("productId") Integer productId){
        if(productId == 0 || productId < 0){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            ProductEntity pro = productRepository.findById(productId).orElse(null);
            if(Objects.nonNull(pro)){
                productRepository.deleteById(productId);
                return new ResponseEntity<>(pro, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }
    }
}
