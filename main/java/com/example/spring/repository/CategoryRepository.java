package com.example.spring.repository;

import com.example.spring.model.entity.Category;
import com.example.spring.model.entity.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    Category findByName(CategoryName categoryName);
}
