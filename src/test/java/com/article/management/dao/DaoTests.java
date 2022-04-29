package com.article.management.dao;

import com.article.management.model.Article;
import com.article.management.util.DateUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DaoTests {
    @Autowired
    IArticleRepository articleRepository;
    @Test
    void testCreateReadArticle() {
        //given
        String publishingDate= DateUtil.convertLocalDateTimeToISO8601(LocalDateTime.now());
        Article article = new Article(1L, "Java", "Yakup Akkin","content", publishingDate);
        //when
        articleRepository.save(article);
        Iterable<Article> articles = articleRepository.findAll();
        //then
        Assertions.assertThat(articles).extracting(Article::getTitle).asList();

    }
}
