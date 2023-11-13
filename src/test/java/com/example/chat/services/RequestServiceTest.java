package com.example.chat.services;

import com.example.chat.rest.models.Request;
import com.example.chat.rest.models.User;
import com.example.chat.rest.repositories.UserRepository;
import com.example.chat.rest.services.RequestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RequestServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private RequestService requestService;

    @Test
    public void testAdd() {
        // Arrange
        Long userId = 1L;
        Request request = new Request(1L, "Test message", LocalDate.now(), null);
        User user = new User(1L, "John", "Doe", "john.doe@example.com", new HashSet<>());

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        // Act
        requestService.add(userId, request);

        // Assert
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(user);
        assertTrue(user.getRequests().contains(request));
    }

    @Test
    public void testAddUserNotFound() {
        // Arrange
        Long userId = 1L;
        Request request = new Request(1L, "Test message", LocalDate.now(), null);

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Assert
        assertThrows(IllegalArgumentException.class, () -> requestService.add(userId, request));
        verify(userRepository, never()).save(any());
    }

    @Test
    void testUpdate() {
        // Arrange
        Long userId = 1L;
        Long requestId = 1L;
        Request existingRequest = new Request(requestId, "Existing message", LocalDate.now(), null);
        Request updatedRequest = new Request(requestId, "Updated message", LocalDate.now(), null);
        User user = new User(userId, "John", "Doe", "john.doe@example.com", new HashSet<>(Set.of(existingRequest)));

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        requestService.update(userId, updatedRequest);

        // Assert
        assertEquals(updatedRequest.getMessage(), existingRequest.getMessage());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testUpdateRequestNotFound() {
        // Arrange
        Long userId = 1L;
        Long requestId = 1L;
        Request existingRequest = new Request(requestId, "Updated message", LocalDate.now(), null);
        Request searchRequest = new Request(2L, "Message", LocalDate.now(), null);
        User user = new User(userId, "John", "Doe", "john.doe@example.com", new HashSet<>(Set.of(existingRequest)));

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Assert
        assertThrows(IllegalArgumentException.class, () -> requestService.update(userId, searchRequest));
        verify(userRepository, never()).save(any());
    }

    @Test
    void testDelete() {
        // Arrange
        Long userId = 1L;
        Long requestId = 1L;
        Request existingRequest = new Request(requestId, "Existing message", LocalDate.now(), null);
        User user = new User(userId, "John", "Doe", "john.doe@example.com", new HashSet<>(Set.of(existingRequest)));

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        requestService.delete(userId, requestId);

        // Assert
        assertFalse(user.getRequests().contains(existingRequest));
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testDeleteRequestNotFound() {
        // Arrange
        Long userId = 1L;
        Long requestId = 1L;
        Long searchRequestId = 2L;
        Request existingRequest = new Request(requestId, "Updated message", LocalDate.now(), null);
        User user = new User(userId, "John", "Doe", "john.doe@example.com", new HashSet<>(Set.of(existingRequest)));

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Assert
        assertThrows(IllegalArgumentException.class, () -> requestService.delete(userId, searchRequestId));
        verify(userRepository, never()).save(any());
    }
}
