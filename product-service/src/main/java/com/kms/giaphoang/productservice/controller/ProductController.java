package com.kms.giaphoang.productservice.controller;

import com.kms.giaphoang.productservice.dto.ProductDto;
import com.kms.giaphoang.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController extends AbstractApplicationController{
    private final ProductService productService;
    @PostMapping
    public ResponseEntity<String> saveProduct(@RequestBody ProductDto productDto){
        final String productId = productService.saveProduct(productDto);
        return new ResponseEntity<>(productId, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        final List<ProductDto> products = productService.getAllProducts().stream()
                .map(mapper::toProductDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }
    @GetMapping("/{skuCode}")
    public ResponseEntity<ProductDto> getProductBySkuCode(@PathVariable String skuCode){
        final ProductDto productDto = mapper.toProductDto(productService.getProductBySkuCode(skuCode));
        return ResponseEntity.ok(productDto);
    }
    @PutMapping("/{skuCode}")
    public ResponseEntity<String> updateProduct(@PathVariable String skuCode, @RequestBody ProductDto productDto){
        final String productId = productService.updateProduct(skuCode, productDto);
        return ResponseEntity.ok(productId);
    }
    @DeleteMapping("/{skuCode}")
    public ResponseEntity<String> deleteProduct(@PathVariable String skuCode){
        productService.deleteProduct(skuCode);
        return ResponseEntity.ok("Delete product successfully");
    }
}
