package com.example.spring.service;

import com.example.spring.model.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void addProduct(Product product);

    List<Product> findAllProducts();

    void delete(String id);

    String totalPrice();
}
