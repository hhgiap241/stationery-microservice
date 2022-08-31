package com.kms.giaphoang.productservice.controller;

import com.kms.giaphoang.productservice.dto.CategoryDto;
import com.kms.giaphoang.productservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 8/26/2022, Friday
 * @project: product-service
 **/

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController extends AbstractApplicationController{
    private final CategoryService categoryService;
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        final List<CategoryDto> categoryList = categoryService.getAllCategories().stream()
                .map(mapper::toCategoryDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(categoryList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable String id){
        final CategoryDto categoryDto = mapper.toCategoryDto(categoryService.getCategoryById(id));
        return ResponseEntity.ok(categoryDto);
    }
    @PostMapping
    public ResponseEntity<String> saveCategory(@RequestBody CategoryDto categoryDto){
        return ResponseEntity.ok(categoryService.saveCategory(categoryDto));
    }
}
