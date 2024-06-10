package com.w4t3rcs.test.repository;

import com.w4t3rcs.test.entity.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ArticleRepository extends ElasticsearchRepository<Article, String> {
    List<Article> findAllByAuthor(String author);

    List<Article> findAllByTitle(String title);

    List<Article> findAllByBody(String body);
}
