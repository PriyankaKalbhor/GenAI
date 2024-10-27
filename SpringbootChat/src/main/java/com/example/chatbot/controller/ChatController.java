package com.example.chatbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.chatbot.dto.ChatRequestDTO;
import com.example.chatbot.dto.ChatResponseDTO;
import com.example.chatbot.service.ChatService;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
    
    @Autowired
    private ChatService chatService;

    @PostMapping
    public ResponseEntity<ChatResponseDTO> chatWithAI(@RequestBody ChatRequestDTO request) {
        String userInput = request.getQuery();
        String aiResponse = chatService.getChatResponse(userInput);
        return ResponseEntity.ok(new ChatResponseDTO(aiResponse));
    }

}
