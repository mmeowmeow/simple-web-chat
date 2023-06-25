package com.example.chat.rest.controllers;

import com.example.chat.rest.models.User;
import com.example.chat.rest.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    Iterable<User> getAllUsers() {
        return userService.get();
    }

    @GetMapping("/{userId}")
    User getUserById(@PathVariable Long userId) {
        return userService.get(userId);
    }

    @RequestMapping(value = "/name", method = RequestMethod.GET)
    User getUserByName(@RequestParam(value = "userName") String userName) {
        return userService.getByName(userName);
    }
}
