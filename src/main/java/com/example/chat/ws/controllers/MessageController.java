package com.example.chat.ws.controllers;

import com.example.chat.ws.models.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;

@Controller
public class MessageController {

    private SimpMessagingTemplate simpMessagingTemplate;

    public MessageController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    // Handles messages from /app/chat. (Spring adds the /app prefix)
    @MessageMapping("/chat")
    // Sends the return value of this method to /topic/messages
    @SendTo("/topic/messages")
    public Message getMessages(@Payload Message message) {
        return message;
    }

    // Handles messages from private-chat, send them to each individual user
    @MessageMapping("/private-chat")
    public Message recMessage(@Payload Message message) {
        message.setDateOfContact(LocalDate.now());
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(), "/private", message);
        return message;
    }

}
