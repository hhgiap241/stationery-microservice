package com.kms.giaphoang.productservice.service.impl;

import com.kms.giaphoang.productservice.dto.ProductDto;
import com.kms.giaphoang.productservice.exception.CategoryNotFoundException;
import com.kms.giaphoang.productservice.model.Category;
import com.kms.giaphoang.productservice.model.Product;
import com.kms.giaphoang.productservice.repository.CategoryRepository;
import com.kms.giaphoang.productservice.repository.ProductRepository;
import com.kms.giaphoang.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public String saveProduct(ProductDto productDto) {
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category " + productDto.getCategoryId() + " not found."));

        Product product = Product.builder()
                .name(productDto.getName())
                .url(productDto.getUrl())
                .description(productDto.getDescription())
                .quantity(productDto.getQuantity())
                .price(productDto.getPrice())
                .category(category)
                .build();
        return productRepository.save(product).getId();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
