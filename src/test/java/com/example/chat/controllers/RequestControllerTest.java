package com.example.chat.controllers;

import com.example.chat.rest.controllers.RequestController;
import com.example.chat.rest.models.Request;
import com.example.chat.rest.services.RequestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RequestControllerTest {
    @Mock
    private RequestService requestService;

    @InjectMocks
    private RequestController requestController;

    @Test
    public void testAddRequest() {
        // Arrange
        Request request = new Request(1L, "Hi...", LocalDate.now(), null);
        doThrow(new IllegalArgumentException("User with ID " + 1L + " is not found")).when(requestService).add(1L, request);

        // Assert
        assertThrows(IllegalArgumentException.class, () -> requestController.addRequest(1L, request));
        verify(requestService, times(1)).add(1L, request);

        // Arrange
        Request request2 = new Request(1L, "Hi...", LocalDate.now(), null);
        ArgumentCaptor<Request> valueCapture = ArgumentCaptor.forClass(Request.class);
        doNothing().when(requestService).add(anyLong(), valueCapture.capture());

        // Act
        requestController.addRequest(1L, request2);

        // Assert
        assertThat(valueCapture.getValue().getMessage()).isEqualTo("Hi...");
        verify(requestService, times(1)).add(1L, request2);
    }
}
