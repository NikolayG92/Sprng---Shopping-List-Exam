package com.example.spring.service;

import com.example.spring.model.entity.Category;
import com.example.spring.model.entity.CategoryName;
import com.example.spring.model.view.CategoryViewModel;

import java.util.List;

public interface CategoryService {
    void initCategories();

    Category find(CategoryName categoryName);

    List<CategoryViewModel> findAllCategories();
}
