package com.example.spring.model.view;

import com.example.spring.model.entity.Product;

import java.util.List;

public class CategoryViewModel {


    private String id;
    private String name;
    private String imageUrl;
    private List<Product> products;

    public CategoryViewModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
