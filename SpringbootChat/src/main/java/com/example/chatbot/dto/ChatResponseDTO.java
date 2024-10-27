package com.example.chatbot.dto;

public class ChatResponseDTO {
    private String response;

    // Constructor
    public ChatResponseDTO(String response) {
        this.response = response;
    }

    // Getters and Setters
    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
