package com.example.chat.rest.services;

import com.example.chat.rest.models.User;
import com.example.chat.rest.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> get() {
        return userRepository.findAll();
    }

    public User get(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User with ID " + userId + " is not found"));
    }

    public User getByName(String userName) {
        return Optional.ofNullable(userRepository.findUserByFirstName(userName)).orElseThrow(() -> new IllegalArgumentException("User with name " + userName + " is not found"));
    }
}
