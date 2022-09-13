package com.kms.giaphoang.productservice.service;

import com.kms.giaphoang.productservice.dto.ProductDto;
import com.kms.giaphoang.productservice.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 8/26/2022, Friday
 * @project: product-service
 **/
public interface ProductService {
    public List<Product> getAllProducts();
    List<Product> findProductsByCategory(String categoryName);
    public String saveProduct(ProductDto productDto);

    Product getProductBySkuCode(String skuCode);

    String updateProduct(String skuCode, ProductDto productDto);
    void deleteProduct(String skuCode);
    Page<Product> getAllProductsWithPagination(Pageable pageable);
}
