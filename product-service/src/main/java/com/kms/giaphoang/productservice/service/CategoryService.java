package com.kms.giaphoang.productservice.service;

import com.kms.giaphoang.productservice.dto.CategoryDto;
import com.kms.giaphoang.productservice.model.Category;

import java.util.List;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 8/26/2022, Friday
 * @project: product-service
 **/
public interface CategoryService {
    public List<Category> getAllCategories();
    Category getCategoryById(String id);
    public String saveCategory(CategoryDto categoryDto);
}
