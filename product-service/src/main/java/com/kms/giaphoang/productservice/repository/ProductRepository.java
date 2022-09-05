package com.kms.giaphoang.productservice.repository;

import com.kms.giaphoang.productservice.model.Category;
import com.kms.giaphoang.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 8/26/2022, Friday
 * @project: product-service
 **/
public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findBySkuCode(String skuCode);
    Optional<Product> findByName(String name);
    List<Product> findByCategory(Category category);
}
