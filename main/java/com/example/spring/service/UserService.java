package com.example.spring.service;

import com.example.spring.model.entity.User;

public interface UserService {
    void register(User user);

    User findByUsername(String username);
}
