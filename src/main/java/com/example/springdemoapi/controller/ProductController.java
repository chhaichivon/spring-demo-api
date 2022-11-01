package com.example.springdemoapi.controller;

import com.example.springdemoapi.model.ProductEntity;
import com.example.springdemoapi.payload.ProductPayload;
import com.example.springdemoapi.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/v1/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductEntity>> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                                       @RequestParam(value = "limit", defaultValue = "30") int limit) {
        if(limit > 30){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(productService.findAll(page,limit), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "{productId}", method = RequestMethod.GET)
    public ResponseEntity<ProductEntity> findById(@PathVariable("productId") Integer productId) {
        if(productId == 0 || productId < 0){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(productService.findById(productId), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<ProductEntity> save(@RequestBody ProductPayload productReq) {
        if(Objects.nonNull(productReq)){
            return new ResponseEntity<>(productService.save(productReq), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.PUT)
    public ResponseEntity<ProductEntity> update(@PathVariable("productId") Integer productId,
                                                @RequestBody ProductPayload productReq) {
        if(productId == 0 || productId < 0){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            if(Objects.nonNull(productReq)){
                return new ResponseEntity<>(productService.update(productId, productReq), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }
    }

    @DeleteMapping(value = "/{productId}")
    public ResponseEntity<ProductEntity> delete(@PathVariable("productId") Integer productId){
        if(productId == 0 || productId < 0){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(productService.delete(productId), HttpStatus.NOT_FOUND);
        }
    }
}
