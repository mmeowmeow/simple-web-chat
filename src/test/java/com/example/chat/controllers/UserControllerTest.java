package com.example.chat.controllers;

import com.example.chat.rest.controllers.UserController;
import com.example.chat.rest.models.User;
import com.example.chat.rest.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        // Arrange
        Set<User> users = new HashSet<>(Arrays.asList(
                new User(1L, "John", "Doe", "john@example.com", null),
                new User(2L, "Jane", "Smith", "jane@example.com", null)
        ));
        when(userService.get()).thenReturn(users);

        // Act
        Iterable<User> result = userController.getAllUsers();

        // Assert
        assertEquals(users, result);
        verify(userService, times(1)).get();
    }

    @Test
    public void testGetUserById() {
        // Arrange
        Long userId = 1L;
        User user = new User(userId, "Joycelin", "Pasticznyk", "john@jpasticznyk0@tuttocitta.it", null);
        when(userService.get(userId)).thenReturn(user);

        // Act
        User result = userController.getUserById(userId);

        // Assert
        assertEquals(user, result);
        verify(userService, times(1)).get(userId);
    }

    @Test
    public void testGetUserByName() {
        // Arrange
        String userName = "Tallie";
        User user = new User(3L, "Tallie", "Petrashkov", "tpetrashkov2@smugmug.com", null);
        when(userService.getByName(userName)).thenReturn(user);

        // Act
        User result = userController.getUserByName(userName);

        // Assert
        assertEquals(user, result);
        verify(userService, times(1)).getByName(userName);
    }
}