package com.kms.giaphoang.productservice.repository;

import com.kms.giaphoang.productservice.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 8/26/2022, Friday
 * @project: product-service
 **/
public interface CategoryRepository extends MongoRepository<Category, String> {
}
