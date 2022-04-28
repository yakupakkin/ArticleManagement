package com.article.management.service;

import com.article.management.dao.IArticleRepository;
import com.article.management.dto.ArticleDTO;
import com.article.management.mapper.ArticleMapper;
import com.article.management.model.Article;
import com.article.management.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ServiceTests {

    @Mock
    IArticleRepository articleRepository;

    @Mock
    IArticleService articleService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOrSaveArticle() {
        //given
        ArticleDTO article = new ArticleDTO("Article about Java", "Yakup Akkin", "Article about Java", LocalDateTime.now());

        // when
        articleService.createArticle(article);
        //then
        verify(articleRepository, times(1)).save(ArticleMapper.INSTANCE.dtoToEntity(article));
    }

    @Test
    void testListArticles(){

        //given
        ArticleDTO article = new ArticleDTO("Article about Java", "Yakup Akkin", "Article about Java", LocalDateTime.now());

        // when
        articleService.createArticle(article);
        verify(articleService, times(1)).createArticle(article);

        // test
        Map<Date,Integer> articleList = articleService.countOfArticlesByLastSevenDays();

        assertEquals(1, articleList.size());
        verify(articleRepository, times(1)).findAll();
    }
}
