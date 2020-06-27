package com.example.spring.repository;

import com.example.spring.model.entity.Category;
import com.example.spring.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Optional<Product> findByName(String name);
    List<Product> findAllByCategory(Category category);
}

