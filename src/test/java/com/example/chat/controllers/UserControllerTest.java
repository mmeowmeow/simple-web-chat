package com.example.chat.controllers;

import com.example.chat.rest.controllers.UserController;
import com.example.chat.rest.models.User;
import com.example.chat.rest.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void testGetAllUsers() {
        // Arrange
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "John", "Doe", "john@example.com", null));
        users.add(new User(2L, "Jane", "Smith", "jane@example.com", null));
        when(userService.get()).thenReturn(users);

        // Act
        Iterable<User> result = userController.getAllUsers();

        // Assert
        assertThat(result.iterator().next().getFirstName()).isEqualTo("John");
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
        assertThat(result.getFirstName()).isEqualTo("Joycelin");
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
        assertThat(result.getFirstName()).isEqualTo("Tallie");
        verify(userService, times(1)).getByName(userName);
    }
}