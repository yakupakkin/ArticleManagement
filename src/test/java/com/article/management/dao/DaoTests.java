package com.article.management.dao;

import com.article.management.model.Article;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Date;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DaoTests {
    @Autowired
    IArticleRepository articleRepository;
    @Test
    void testCreateReadDelete() {
        //given
        Article article = new Article(1, "Java", "Yakup Akkin","content", LocalDateTime.now());
        //when
        articleRepository.save(article);
        Iterable<Article> articles = articleRepository.findAll();
        //then
        Assertions.assertThat(articles).extracting(Article::getTitle).asList();

        articleRepository.deleteAll();
        Assertions.assertThat(articleRepository.findAll()).isEmpty();
    }
}
