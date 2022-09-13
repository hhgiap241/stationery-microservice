package com.kms.giaphoang.productservice.service.impl;

import com.kms.giaphoang.productservice.dto.ProductDto;
import com.kms.giaphoang.productservice.exception.CategoryNotFoundException;
import com.kms.giaphoang.productservice.exception.ProductNotFoundException;
import com.kms.giaphoang.productservice.model.Category;
import com.kms.giaphoang.productservice.model.Product;
import com.kms.giaphoang.productservice.repository.CategoryRepository;
import com.kms.giaphoang.productservice.repository.ProductRepository;
import com.kms.giaphoang.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        productRepository.findByName(productDto.getName()).ifPresent(product -> {
            throw new ProductNotFoundException("Product " + productDto.getName() + " already existed.");
        });
        Product product = Product.builder()
                .name(productDto.getName())
                .url(productDto.getUrl())
                .description(productDto.getDescription())
                .skuCode(productDto.getName().replace(" ", "_"))
                .price(productDto.getPrice())
                .category(category)
                .build();
        return productRepository.save(product).getId();
    }

//    @Override
//    public List<Product> getAllProducts() {
//        return productRepository.findAll();
//    }

    @Override
    public Product getProductBySkuCode(String skuCode) {
        return productRepository.findBySkuCode(skuCode)
                .orElseThrow(() -> new ProductNotFoundException("Product " + skuCode + " not found."));
    }

    @Override
    public String updateProduct(String skuCode, ProductDto productDto) {
        final Product product = productRepository.findBySkuCode(skuCode)
                .orElseThrow(() -> new ProductNotFoundException("Product " + skuCode + " not found."));
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category " + productDto.getCategoryId() + " not found."));
        product.setName(productDto.getName());
        product.setSkuCode(productDto.getName().replace(" ", "_"));
        product.setUrl(productDto.getUrl());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCategory(category);
        return productRepository.save(product).getId();
    }

    @Override
    public void deleteProduct(String skuCode) {
        final Product product = productRepository.findBySkuCode(skuCode)
                .orElseThrow(() -> new ProductNotFoundException("Product " + skuCode + " not found."));
        productRepository.delete(product);
    }

    @Override
    public List<Product> findProductsByCategory(String categoryName) {
        if (categoryName.equals("All")) {
            return productRepository.findAll();
        }
        final Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new CategoryNotFoundException("Category " + categoryName + " not found."));
        return productRepository.findByCategory(category);
    }

    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        Page<Product> result = productRepository.findAll(pageable);
        return result;
    }
}
