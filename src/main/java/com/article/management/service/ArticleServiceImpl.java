package com.article.management.service;

import com.article.management.dao.IArticleRepository;
import com.article.management.dto.ArticleDTO;
import com.article.management.mapper.ArticleMapper;
import com.article.management.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Service
public class ArticleServiceImpl implements IArticleService{

    @Autowired
    IArticleRepository articleRepository;
    @Override
    public void createArticle(ArticleDTO articleDTO) {
        articleRepository.save(ArticleMapper.INSTANCE.dtoToEntity(articleDTO));
    }

    @Override
    public Map<Date,Integer> countOfArticlesByLastSevenDays() {
        LocalDateTime startDate = LocalDateTime.now().minusDays(7);
        List<Article> result = articleRepository.findAllByPublishingDateBetween(startDate, LocalDateTime.now());
        Map<Date,Integer>  map = new HashMap<>();

        if (!result.isEmpty()) {
            return map;
        } else {
            return new HashMap<Date,Integer>();
        }
    }
}
