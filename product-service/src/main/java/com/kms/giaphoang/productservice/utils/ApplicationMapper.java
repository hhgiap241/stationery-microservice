package com.kms.giaphoang.productservice.utils;

import com.kms.giaphoang.productservice.dto.CategoryDto;
import com.kms.giaphoang.productservice.dto.ProductDto;
import com.kms.giaphoang.productservice.model.Category;
import com.kms.giaphoang.productservice.model.Product;
import org.springframework.stereotype.Component;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 8/26/2022, Friday
 * @project: product-service
 **/
@Component
public class ApplicationMapper {
    public ProductDto toProductDto(Product product){
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .url(product.getUrl())
                .description(product.getDescription())
                .skuCode(product.getSkuCode())
                .price(product.getPrice())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .categoryId(product.getCategory().getId())
                .categoryName(product.getCategory().getName())
                .build();
    }
    public CategoryDto toCategoryDto(Category category){
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .build();
    }
}
