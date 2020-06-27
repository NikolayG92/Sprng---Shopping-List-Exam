package com.example.spring.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity{

    private CategoryName name;
    private String description;
    private List<Product> products;

    public Category() {
    }

    public Category(CategoryName name, String description) {
        this.name = name;
        this.description = description;
    }

    @Column(name = "name", unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    public CategoryName getName() {
        return name;
    }

    public void setName(CategoryName name) {
        this.name = name;
    }

    @Column(name = "description", columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
