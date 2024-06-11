package com.w4t3rcs.test.dto;

import com.w4t3rcs.test.entity.Article;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ArticleDto {
    private String id;
    private String author;
    private String title;
    private String body;

    public static ArticleDto fromArticle(@Valid Article article) {
        return ArticleDto.builder()
                .id(article.getId())
                .author(article.getAuthor())
                .title(article.getTitle())
                .body(article.getBody())
                .build();
    }

    @Valid
    public Article toArticle() {
        return Article.builder()
                .id(this.getId())
                .author(this.getAuthor())
                .title(this.getTitle())
                .body(this.getBody())
                .build();
    }
}
