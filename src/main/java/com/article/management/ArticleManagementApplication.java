package com.article.management;

import com.article.management.dao.IArticleRepository;
import com.article.management.model.Article;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class ArticleManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArticleManagementApplication.class, args);
	}

}
