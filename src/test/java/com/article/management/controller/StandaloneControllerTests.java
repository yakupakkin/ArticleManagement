package com.article.management.controller;

import com.article.management.dto.ArticleDTO;
import com.article.management.service.ArticleServiceImpl;
import com.article.management.util.DateUtil;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        String publishingDate= DateUtil.convertLocalDateTimeToISO8601(LocalDateTime.now());
        ArticleDTO article = new ArticleDTO("Article", "Java", "Yakup Akkin", publishingDate);

        //then
        mockMvc.perform(get("/article/count")).andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1))).andExpect(jsonPath("$[0].title", Matchers.is("Article")));
    }

    @Test
    void testCreateArticle() throws Exception {
        //given
        String publishingDate= DateUtil.convertLocalDateTimeToISO8601(LocalDateTime.now());
        ArticleDTO articleDTO = new ArticleDTO("Title Of Article", "Java", "Yakup Akkin", publishingDate);
        articleService.createArticle(articleDTO);
        //when
        verify(articleService, times(1)).createArticle(articleDTO);

        //then
        mockMvc.perform(post("/api/articles",articleDTO)).andExpect(status().isOk());
    }

}
