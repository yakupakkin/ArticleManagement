package com.article.management.service;

import com.article.management.dao.IArticleRepository;
import com.article.management.dto.ArticleDTO;
import com.article.management.mapper.ArticleMapper;
import com.article.management.util.DateUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ServiceTests {

    @Mock
    IArticleRepository articleRepository;

    @InjectMocks
    ArticleServiceImpl articleService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOrSaveArticle() {
        String publishingDate= DateUtil.convertLocalDateTimeToISO8601(LocalDateTime.now());
        //given
        ArticleDTO article = new ArticleDTO("Article about Java", "Yakup Akkin", "Article about Java", publishingDate);

        // when
        articleService.createArticle(article);
        //then
        verify(articleRepository, times(1)).save(ArticleMapper.INSTANCE.dtoToEntity(article));
    }

    @Test
    void testFindAllArticles(){

        String publishingDate= DateUtil.convertLocalDateTimeToISO8601(LocalDateTime.now().minusDays(3));
        ArticleDTO article = new ArticleDTO("Article about Java", "Yakup Akkin", "Article about Java", publishingDate);

        // when
        articleService.createArticle(article);
        //then
        verify(articleService, times(1)).createArticle(article);

        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        verify(articleService).findAll(pageableCaptor.capture());
        assertEquals(1, pageableCaptor.getValue().getPageNumber());

    }

    @Test
    void testCountOfArticlesByLastSevenDays(){

        String publishingDate= DateUtil.convertLocalDateTimeToISO8601(LocalDateTime.now());
        //given
        ArticleDTO article = new ArticleDTO("Article about Java", "Yakup Akkin", "Article about Java",publishingDate );

        // when
        articleService.createArticle(article);
        // test
        Map<String,Integer> articleList = articleService.countOfArticlesByLastSevenDays();

        assertEquals(1, articleList.size());
        verify(articleRepository, times(1)).findAll();
    }
}
