package com.kms.giaphoang.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 8/26/2022, Friday
 * @project: product-service
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "product")
public class Product implements Serializable {
    @Id
    private String id;
    private String name;
    private String url;
    private String description;
    private String skuCode;
    private Double price;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @DBRef
    private Category category;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(url, product.url) && Objects.equals(description, product.description) && Objects.equals(skuCode, product.skuCode) && Objects.equals(price, product.price) && Objects.equals(createdAt, product.createdAt) && Objects.equals(updatedAt, product.updatedAt) && Objects.equals(category, product.category);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, url, description, skuCode, price, createdAt, updatedAt, category);
    }
}
