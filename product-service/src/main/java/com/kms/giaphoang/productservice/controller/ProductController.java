package com.kms.giaphoang.productservice.controller;

import com.kms.giaphoang.productservice.dto.ProductDto;
import com.kms.giaphoang.productservice.model.Product;
import com.kms.giaphoang.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 8/26/2022, Friday
 * @project: product-service
 **/
@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController extends AbstractApplicationController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<String> saveProduct(@RequestBody ProductDto productDto) {
        final String productId = productService.saveProduct(productDto);
        return new ResponseEntity<>(productId, HttpStatus.CREATED);
    }

    //    @GetMapping
//    public ResponseEntity<List<ProductDto>> getAllProducts() {
//        final List<ProductDto> products = productService.getAllProducts().stream()
//                .map(mapper::toProductDto)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(products);
//    }
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllProduct(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("price").ascending());
        final Page<Product> page1 = productService.getAllProducts(pageable);
        List<Product> products = page1.getContent();
        if (products.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        Map<String, Object> response = new HashMap<>();
        response.put("products", products.stream().map(mapper::toProductDto).collect(Collectors.toList()));
        response.put("currentPage", page1.getNumber());
        response.put("totalItems", page1.getTotalElements());
        response.put("totalPages", page1.getTotalPages());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{skuCode}")
    public ResponseEntity<ProductDto> getProductBySkuCode(@PathVariable String skuCode) {
        final ProductDto productDto = mapper.toProductDto(productService.getProductBySkuCode(skuCode));
        return ResponseEntity.ok(productDto);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ProductDto>> findProductByCategory(@RequestParam String category) {
        final List<ProductDto> products = productService.findProductsByCategory(category).stream()
                .map(mapper::toProductDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{skuCode}")
    public ResponseEntity<String> updateProduct(@PathVariable String skuCode, @RequestBody ProductDto productDto) {
        final String productId = productService.updateProduct(skuCode, productDto);
        return ResponseEntity.ok(productId);
    }

    @DeleteMapping("/{skuCode}")
    public ResponseEntity<String> deleteProduct(@PathVariable String skuCode) {
        productService.deleteProduct(skuCode);
        return ResponseEntity.ok("Delete product successfully");
    }
}
