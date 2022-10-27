package com.example.springdemoapi.service;

import com.example.springdemoapi.model.CategoryEntity;
import com.example.springdemoapi.payload.CategoryPayload;
import com.example.springdemoapi.repository.CategoryRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryEntity> findAll(Integer page, Integer size){
        return categoryRepository.findAll(PageRequest.of(page,size)).getContent();
    }

    public CategoryEntity findById(Integer categoryId){
        return categoryRepository.findById(categoryId).orElse(null);
    }

    public CategoryEntity save(CategoryPayload categoryPayload){
        CategoryEntity categoryEntity = new CategoryEntity();
        return categoryRepository.save(categoryEntity);
    }

    public CategoryEntity update(Integer categoryId, CategoryPayload categoryPayload){
        CategoryEntity categoryEntity = findById(categoryId);
        return categoryRepository.save(categoryEntity);
    }

    public CategoryEntity deleteById(Integer categoryId){
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId).orElse(null);
        if(Objects.nonNull(categoryEntity))
            categoryRepository.deleteById(categoryId);
        return categoryEntity;
    }

}
