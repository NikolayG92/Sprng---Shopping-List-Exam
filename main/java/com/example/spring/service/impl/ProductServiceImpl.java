package com.example.spring.service.impl;

import com.example.spring.model.entity.Product;
import com.example.spring.repository.ProductRepository;
import com.example.spring.service.CategoryService;
import com.example.spring.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public void addProduct(Product product) {

        product.setCategory(this.categoryService.find(product.getCategory().getName()));
        this.productRepository.saveAndFlush(product);
    }

    @Override
    public List<Product> findAllProducts() {

        return this.productRepository.findAll();
    }

    @Override
    public void delete(String id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public String totalPrice() {

        final BigDecimal[] totalPrice = {BigDecimal.ZERO};
        this.productRepository.findAll()
                .forEach(product -> {
                    totalPrice[0] = totalPrice[0].add(product.getPrice());
                });

        return totalPrice[0].toString();
    }
}
