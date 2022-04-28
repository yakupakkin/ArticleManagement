package com.article.management.service;

import com.article.management.dto.ArticleDTO;
import com.article.management.model.Article;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IArticleService {
    void createArticle(ArticleDTO articleDTO);

    Map<Date,Integer> countOfArticlesByLastSevenDays();
}
