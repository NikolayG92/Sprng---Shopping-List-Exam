package com.example.spring.service.impl;

import com.example.spring.model.entity.Category;
import com.example.spring.model.entity.CategoryName;
import com.example.spring.model.view.CategoryViewModel;
import com.example.spring.repository.CategoryRepository;
import com.example.spring.repository.ProductRepository;
import com.example.spring.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
    }

    @Override
    public void initCategories() {
        if(this.categoryRepository.count() == 0){
            Arrays.stream(CategoryName.values())
                    .forEach(categoryName -> {
                        this.categoryRepository
                                .save(new Category(categoryName,
                                        String.format("Description for %s",
                                                categoryName.name())));
                    });
        }
    }

    @Override
    public Category find(CategoryName categoryName) {
        return this.categoryRepository.findByName(categoryName);
    }

    @Override
    public List<CategoryViewModel> findAllCategories() {

        return this.categoryRepository.findAll()
                .stream()
                .map(category -> {
                    CategoryViewModel categoryViewModel =
                            this.modelMapper.map(category, CategoryViewModel.class);
                    categoryViewModel.setImageUrl
                            (String.format("/img/%s.png",
                                    category.getName()).toLowerCase());
                    categoryViewModel.setProducts(
                            this.productRepository.findAllByCategory(category));
                            return categoryViewModel;
                }).collect(Collectors.toList());
    }
}
