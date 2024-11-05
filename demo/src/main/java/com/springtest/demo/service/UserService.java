package com.springtest.demo.service;

import com.springtest.demo.Repository.UserRepository;
import com.springtest.demo.model.User;

public class UserService {
    private final UserRepository userRepository;
    private  User user;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(String userId, String name) {
        User user = new User(userId, name);
        userRepository.save(user);
    }
}
