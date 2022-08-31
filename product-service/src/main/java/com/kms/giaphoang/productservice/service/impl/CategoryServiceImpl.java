package com.kms.giaphoang.productservice.service.impl;

import com.kms.giaphoang.productservice.dto.CategoryDto;
import com.kms.giaphoang.productservice.exception.CategoryExistedException;
import com.kms.giaphoang.productservice.exception.CategoryNotFoundException;
import com.kms.giaphoang.productservice.model.Category;
import com.kms.giaphoang.productservice.repository.CategoryRepository;
import com.kms.giaphoang.productservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 8/26/2022, Friday
 * @project: product-service
 **/
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public String saveCategory(CategoryDto categoryDto) {
        categoryRepository.findByName(categoryDto.getName()).ifPresent(category -> {
            throw new CategoryExistedException("Category already exists");
        });
        Category category = Category.builder()
                .name(categoryDto.getName())
                .build();
        return categoryRepository.save(category).getId();
    }

    @Override
    public Category getCategoryById(String id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category " + id + " not found."));
    }
}
