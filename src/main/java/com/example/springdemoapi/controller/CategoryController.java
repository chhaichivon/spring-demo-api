package com.example.springdemoapi.controller;

import com.example.springdemoapi.model.Category;
import com.example.springdemoapi.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/v1/category")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public ResponseEntity<List<Category>> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                                  @RequestParam(value = "limit", defaultValue = "30") int limit) {
        if(limit > 30){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "{categoryId}", method = RequestMethod.GET)
    public ResponseEntity<Category> findById(@PathVariable("categoryId") Integer categoryId) {
        if(categoryId == 0 || categoryId < 0){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(categoryRepository.findById(categoryId).orElse(null), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Category> save(@RequestBody Category category) {
        if(Objects.isNull(category)){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(categoryRepository.save(category), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{categoryId}", method = RequestMethod.PUT)
    public ResponseEntity<Category> update(@PathVariable("categoryId") Integer categoryId,
                                          @RequestBody Category category) {
        if(categoryId == 0 || categoryId < 0){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            Category cat = categoryRepository.findById(categoryId).orElse(null);
            if(Objects.nonNull(cat)){
                category.setId(categoryId);
                return new ResponseEntity<>(categoryRepository.save(category), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }
    }

    @DeleteMapping(value = "/{categoryId}")
    public ResponseEntity<Category> delete(@PathVariable("categoryId") Integer categoryId){
        if(categoryId == 0 || categoryId < 0){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            Category category = categoryRepository.findById(categoryId).orElse(null);
            if(Objects.nonNull(category)){
                categoryRepository.deleteById(categoryId);
                return new ResponseEntity<>(category, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }
    }
}
