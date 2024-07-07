package com.w4t3rcs.test.controller;

import com.w4t3rcs.test.dto.ArticleDto;
import com.w4t3rcs.test.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

    @Operation(summary = "Getting articles from Elasticsearch", parameters = {
            @Parameter(name = "author", description = "Author of needed articles"),
            @Parameter(name = "title", description = "Title of needed articles"),
            @Parameter(name = "body", description = "Body (content) of needed articles")
    })
    @GetMapping(params = {"author", "title", "body"})
    public List<ArticleDto> getArticles(@RequestParam(required = false) String author, @RequestParam(required = false) String title, @RequestParam(required = false) String body) {
        if (author != null) {
            return articleService.getArticlesByAuthor(author);
        } else if (title != null) {
            return articleService.getArticlesByTitle(title);
        } else if (body != null) {
            return articleService.getArticlesByBody(body);
        } else {
            return articleService.getArticles();
        }
    }

    @Operation(summary = "Getting an article from Elasticsearch by id", parameters = {
            @Parameter(name = "id", description = "Id of needed article", example = "1", required = true)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ArticleDto> getArticleById(@PathVariable String id) {
        return ResponseEntity.ok(articleService.getArticleById(id));
    }

    @Operation(summary = "Getting AI generated article")
    @GetMapping("/ai")
    public ResponseEntity<ArticleDto> getArticleFromAI() {
        return ResponseEntity.ok(articleService.getAIGeneratedArticle());
    }

    @Operation(summary = "Creating an article and saving it to Elasticsearch")
    @PostMapping
    public ResponseEntity<ArticleDto> postArticle(@Valid @RequestBody ArticleDto articleDto) {
        return ResponseEntity.ok(articleService.createArticle(articleDto));
    }
}
