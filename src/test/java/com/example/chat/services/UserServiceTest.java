package com.example.chat.services;

import com.example.chat.rest.models.User;
import com.example.chat.rest.repositories.UserRepository;
import com.example.chat.rest.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetAll() {
        // Arrange
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "John", "Doe", "john@example.com", null));
        users.add(new User(2L, "Jane", "Smith", "jane@example.com", null));
        when(userRepository.findAll()).thenReturn(users);

        // Act
        Iterable<User> result = userService.get();

        // Assert
        assertThat(result.iterator().next().getFirstName()).isEqualTo("John");
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testGet() {
        // Arrange
        doThrow(new IllegalArgumentException("User with ID " + 333L + " is not found")).when(userRepository).findById(333L);

        // Assert
        assertThrows(IllegalArgumentException.class, () -> userService.get(333L));
        verify(userRepository, times(1)).findById(333L);

        // Arrange
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "John", "Doe", "john@example.com", null));
        users.add(new User(2L, "Jane", "Smith", "jane@example.com", null));
        when(userRepository.findById(1L)).thenReturn(users.stream().findFirst());

        // Act
        User result = userService.get(1L);

        // Assert
        assertThat(result.getFirstName()).isEqualTo("John");
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetByName() {
        // Arrange
        String userName = "abc";
        doThrow(new IllegalArgumentException("User with name" + userName + " is not found")).when(userRepository).findUserByFirstName(userName);

        // Assert
        assertThrows(IllegalArgumentException.class, () -> userService.getByName(userName));
        verify(userRepository, times(1)).findUserByFirstName(userName);

        // Arrange
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "John", "Doe", "john@example.com", null));
        users.add(new User(2L, "Jane", "Smith", "jane@example.com", null));
        when(userRepository.findUserByFirstName("John")).thenReturn(users.stream().findFirst().get());

        // Act
        User result = userService.getByName("John");

        // Assert
        assertThat(result.getFirstName()).isEqualTo("John");
        verify(userRepository, times(1)).findUserByFirstName("John");
    }
}
