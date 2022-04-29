package com.article.management.service;

import com.article.management.dto.ArticleDTO;
import com.article.management.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IArticleService {
    void createArticle(ArticleDTO articleDTO);
    Page<Article> findAll(Pageable pageable);

    ArticleDTO findOne(Long id);
    Map<String, Integer> countOfArticlesByLastSevenDays();
}
