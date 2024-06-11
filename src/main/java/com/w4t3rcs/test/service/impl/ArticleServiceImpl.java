package com.w4t3rcs.test.service.impl;

import com.w4t3rcs.test.dto.ArticleDto;
import com.w4t3rcs.test.exception.ArticleNotFoundException;
import com.w4t3rcs.test.repository.ArticleRepository;
import com.w4t3rcs.test.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;

    @Override
    public ArticleDto createArticle(ArticleDto articleDto) {
        ArticleDto saved = ArticleDto.fromArticle(articleRepository.save(articleDto.toArticle()));
        log.info("Article: \"{}\" has been saved into DB", saved);
        return saved;
    }

    @Override
    public List<ArticleDto> getArticles() {
        List<ArticleDto> articleDtos = articleRepository.findAll(Pageable.ofSize(25))
                .stream()
                .map(ArticleDto::fromArticle)
                .toList();
        log.info("Articles have been got from DB");
        return articleDtos;
    }

    @Override
    public List<ArticleDto> getArticlesByAuthor(String author) {
        List<ArticleDto> articleDtos = articleRepository.findAllByAuthor(author)
                .stream()
                .map(ArticleDto::fromArticle)
                .toList();
        log.info("Articles by author: \"{}\" have been got from DB", author);
        return articleDtos;
    }

    @Override
    public List<ArticleDto> getArticlesByTitle(String title) {
        List<ArticleDto> articleDtos = articleRepository.findAllByTitle(title)
                .stream()
                .map(ArticleDto::fromArticle)
                .toList();
        log.info("Articles by title: \"{}\" have been got from DB", title);
        return articleDtos;
    }

    @Override
    public List<ArticleDto> getArticlesByBody(String body) {
        List<ArticleDto> articleDtos = articleRepository.findAllByBody(body)
                .stream()
                .map(ArticleDto::fromArticle)
                .toList();
        log.info("Articles by body: \"{}\" have been got from DB", body);
        return articleDtos;
    }

    @Override
    public ArticleDto getArticleById(String id) {
        ArticleDto articleDto = ArticleDto.fromArticle(articleRepository.findById(id)
                .orElseThrow(ArticleNotFoundException::new));
        log.info("Article by id: \"{}\" has been got from DB", id);
        return articleDto;
    }


    @Override
    public String deleteArticle(String id) {
        articleRepository.deleteById(id);
        log.info("Article by id: \"{}\" has been removed from DB", id);
        return id;
    }
}
