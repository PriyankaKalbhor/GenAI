package com.example.chatbot.service;

import org.springframework.core.ParameterizedTypeReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.PostConstruct;

@Service
public class ChatService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpHeaders headers;

    @Value("${openai.api.key}")
    private String apiKey;

    @PostConstruct
    public void init() {
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");
    }

    private static final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";
    // "https://api.openai.com/v1/completions";

    public String getChatResponse(String userInput) {
        // Construct request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo"); // You can change the model as needed
        requestBody.put("prompt", userInput);
        requestBody.put("max_tokens", 150);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        try {
            // Send POST request to OpenAI API with parameterized type reference
            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                OPENAI_API_URL,
                org.springframework.http.HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<Map<String, Object>>() {}
            );

            // Parse response and return
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                Object choicesObject = response.getBody().get("choices");

                // Type checking for List<Map<String, Object>>
                if (choicesObject instanceof List<?>) {
                    List<?> choicesList = (List<?>) choicesObject;

                    if (!choicesList.isEmpty() && choicesList.get(0) instanceof Map<?, ?>) {
                        @SuppressWarnings("unchecked")
                        List<Map<String, Object>> choices = (List<Map<String, Object>>) choicesList;
                        Map<String, Object> firstChoice = choices.get(0);
                        return (String) firstChoice.get("text");
                    } else {
                        throw new IllegalArgumentException("Invalid response format: choices is not a List<Map<String, Object>>");
                    }
                } else {
                    throw new IllegalArgumentException("Invalid response format: choices is not a List");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error retrieving response from OpenAI";
        }

        return "No response from AI";
    }
}
