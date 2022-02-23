package com.example.servingwebcontent.service.impl;

import com.example.servingwebcontent.model.User;
import com.example.servingwebcontent.repository.UserRepository;
import com.example.servingwebcontent.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

}
