package com.w4t3rcs.test.service.impl;

import com.w4t3rcs.test.dto.ArticleDto;
import com.w4t3rcs.test.entity.Article;
import com.w4t3rcs.test.exception.ArticleNotFoundException;
import com.w4t3rcs.test.repository.ArticleRepository;
import com.w4t3rcs.test.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;

    @Override
    public ArticleDto createArticle(ArticleDto articleDto) {
        return ArticleDto.fromArticle(articleRepository.save(articleDto.toArticle()));
    }

    @Override
    public List<ArticleDto> getArticles() {
        return articleRepository.findAll(Pageable.ofSize(25))
                .stream()
                .map(ArticleDto::fromArticle)
                .toList();
    }

    @Override
    public List<ArticleDto> getArticlesByAuthor(String author) {
        return articleRepository.findAllByAuthor(author)
                .stream()
                .map(ArticleDto::fromArticle)
                .toList();
    }

    @Override
    public List<ArticleDto> getArticlesByTitle(String title) {
        return articleRepository.findAllByTitle(title)
                .stream()
                .map(ArticleDto::fromArticle)
                .toList();
    }

    @Override
    public List<ArticleDto> getArticlesByBody(String body) {
        return articleRepository.findAllByBody(body)
                .stream()
                .map(ArticleDto::fromArticle)
                .toList();
    }

    @Override
    public ArticleDto getArticleById(String id) {
        return ArticleDto.fromArticle(articleRepository.findById(id)
                .orElseThrow(ArticleNotFoundException::new));
    }


    @Override
    public String deleteArticle(String id) {
        articleRepository.deleteById(id);
        return id;
    }
}
