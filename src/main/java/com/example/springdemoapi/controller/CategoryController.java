package com.example.springdemoapi.controller;

import com.example.springdemoapi.model.CategoryEntity;
import com.example.springdemoapi.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/v1/category")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public ResponseEntity<List<CategoryEntity>> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                                        @RequestParam(value = "limit", defaultValue = "30") int limit) {
        if(limit > 30){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "{categoryId}", method = RequestMethod.GET)
    public ResponseEntity<CategoryEntity> findById(@PathVariable("categoryId") Integer categoryId) {
        if(categoryId == 0 || categoryId < 0){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(categoryRepository.findById(categoryId).orElse(null), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<CategoryEntity> save(@RequestBody CategoryEntity categoryEntity) {
        if(Objects.isNull(categoryEntity)){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(categoryRepository.save(categoryEntity), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{categoryId}", method = RequestMethod.PUT)
    public ResponseEntity<CategoryEntity> update(@PathVariable("categoryId") Integer categoryId,
                                                 @RequestBody CategoryEntity categoryEntity) {
        if(categoryId == 0 || categoryId < 0){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            CategoryEntity cat = categoryRepository.findById(categoryId).orElse(null);
            if(Objects.nonNull(cat)){
                categoryEntity.setId(categoryId);
                return new ResponseEntity<>(categoryRepository.save(categoryEntity), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }
    }

    @DeleteMapping(value = "/{categoryId}")
    public ResponseEntity<CategoryEntity> delete(@PathVariable("categoryId") Integer categoryId){
        if(categoryId == 0 || categoryId < 0){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            CategoryEntity categoryEntity = categoryRepository.findById(categoryId).orElse(null);
            if(Objects.nonNull(categoryEntity)){
                categoryRepository.deleteById(categoryId);
                return new ResponseEntity<>(categoryEntity, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }
    }
}
