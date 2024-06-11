package com.w4t3rcs.test.controller;

import com.w4t3rcs.test.dto.ArticleDto;
import com.w4t3rcs.test.service.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping
    public List<ArticleDto> getArticles() {
        return articleService.getArticles();
    }

    @GetMapping(params = "author")
    public List<ArticleDto> getArticleByAuthor(@RequestParam String author) {
        return articleService.getArticlesByAuthor(author);
    }

    @GetMapping(params = "title")
    public List<ArticleDto> getArticleByTitle(@RequestParam String title) {
        return articleService.getArticlesByTitle(title);
    }

    @GetMapping(params = "body")
    public List<ArticleDto> getArticleByBody(@RequestParam String body) {
        return articleService.getArticlesByBody(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDto> getArticleById(@PathVariable String id) {
        return ResponseEntity.ok(articleService.getArticleById(id));
    }

    @PostMapping
    public ResponseEntity<ArticleDto> postArticle(@Valid @RequestBody ArticleDto articleDto) {
        return ResponseEntity.ok(articleService.createArticle(articleDto));
    }
}
