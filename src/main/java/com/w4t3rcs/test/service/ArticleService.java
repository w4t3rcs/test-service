package com.w4t3rcs.test.service;

import com.w4t3rcs.test.dto.ArticleDto;

import java.util.List;

public interface ArticleService {
    ArticleDto createArticle(ArticleDto articleDto);

    List<ArticleDto> getArticles();

    List<ArticleDto> getArticlesByAuthor(String author);

    List<ArticleDto> getArticlesByTitle(String title);

    List<ArticleDto> getArticlesByBody(String body);

    ArticleDto getArticleById(String id);

    String deleteArticle(String id);
}
