package com.example.springdemoapi.controller;

import com.example.springdemoapi.model.CategoryEntity;
import com.example.springdemoapi.payload.CategoryPayload;
import com.example.springdemoapi.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryEntity>> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                                        @RequestParam(value = "limit", defaultValue = "30") int limit) {
        if(limit > 30){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(categoryService.findAll(page,limit), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "{categoryId}", method = RequestMethod.GET)
    public ResponseEntity<CategoryEntity> findById(@PathVariable("categoryId") Integer categoryId) {
        if(categoryId == 0 || categoryId < 0){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(categoryService.findById(categoryId), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<CategoryEntity> save(@RequestBody CategoryPayload categoryPayload) {
        if(Objects.isNull(categoryPayload)){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(categoryService.save(categoryPayload), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{categoryId}", method = RequestMethod.PUT)
    public ResponseEntity<CategoryEntity> update(@PathVariable("categoryId") Integer categoryId,
                                                 @RequestBody CategoryPayload categoryPayload) {
        if(categoryId == 0 || categoryId < 0){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(categoryService.update(categoryId, categoryPayload), HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/{categoryId}")
    public ResponseEntity<CategoryEntity> delete(@PathVariable("categoryId") Integer categoryId){
        if(categoryId == 0 || categoryId < 0){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(categoryService.deleteById(categoryId), HttpStatus.OK);
        }
    }
}
