package com.example.chat.ws.models;

import java.time.LocalDate;

public class Message {
    private String senderName;
    private String receiverName;
    private String message;
    private LocalDate dateOfContact;
    private String status;

    public Message() {
    }

    public Message(String senderName, String receiverName, String message, LocalDate dateOfContact, String status) {
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.message = message;
        this.dateOfContact = dateOfContact;
        this.status = status;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getDateOfContact() {
        return dateOfContact;
    }

    public void setDateOfContact(LocalDate dateOfContact) {
        this.dateOfContact = dateOfContact;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Message{" +
                "senderName='" + senderName + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", message='" + message + '\'' +
                ", dateOfContact=" + dateOfContact +
                ", status='" + status + '\'' +
                '}';
    }
}
