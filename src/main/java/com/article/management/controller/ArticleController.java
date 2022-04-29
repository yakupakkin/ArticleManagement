package com.article.management.controller;

import com.article.management.dto.ArticleDTO;
import com.article.management.model.Article;
import com.article.management.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ArticleController {

    @Autowired
    IArticleService articleService;

    @PostMapping(value = "/articles")
    public void addArticle(@RequestBody ArticleDTO articleDTO) {
        articleService.createArticle(articleDTO);
    }

    @GetMapping("/articles/{id}")
    public ArticleDTO getArticle(@PathVariable Long id) {
        return  articleService.findOne(id);
    }
    @GetMapping("/articles")
    public Page<Article> getAllArticles(Pageable pageable) {
        return articleService.findAll(pageable);
    }

    @GetMapping("/count")
    public Map<String, Integer> countOfArticlesByLastSevenDays() {
        return articleService.countOfArticlesByLastSevenDays();
    }

}


