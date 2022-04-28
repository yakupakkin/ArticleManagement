package com.article.management.controller;

import com.article.management.dto.ArticleDTO;
import com.article.management.service.ArticleServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ArticleController.class)
public class StandaloneControllerTests {

    @MockBean
    ArticleServiceImpl articleService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void testCountOfArticlesByLastSevenDays() throws Exception {
        //given
        ArticleDTO article = new ArticleDTO("Article", "Java", "Yakup Akkin", LocalDateTime.now());
       Map<Date,Integer> articles = new HashMap<>();
        articles.put(new Date(),1);
        //when
        Mockito.when(articleService.countOfArticlesByLastSevenDays()).thenReturn(articles);
        //then
        mockMvc.perform(get("/api/article")).andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1))).andExpect(jsonPath("$[0].name", Matchers.is("Java")));
    }

    @Test
    void testCreateArticle() throws Exception {
        //given
        ArticleDTO articleDTO = new ArticleDTO("Title Of Article", "Java", "Yakup Akkin", LocalDateTime.now());

        //when
        verify(articleService, times(1)).createArticle(articleDTO);

        //then
        mockMvc.perform(put("/api/article",articleDTO)).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}
