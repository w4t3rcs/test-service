package com.w4t3rcs.test.controller;

import com.w4t3rcs.test.dto.ArticleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1.0/ai")
@RequiredArgsConstructor
public class AIController {
    private final ChatClient chatClient;

    @GetMapping
    public ResponseEntity<ArticleDto> getResponse() {
        return ResponseEntity.ok(chatClient.prompt()
                .user("Generate an Article")
                .call()
                .entity(ArticleDto.class));
    }
}
