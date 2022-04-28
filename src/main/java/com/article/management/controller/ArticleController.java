package com.article.management.controller;

import com.article.management.dto.ArticleDTO;
import com.article.management.model.User;
import com.article.management.service.IArticleService;
import com.article.management.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    IArticleService articleService;
    @Autowired
    IUserService userService;

    @PostMapping(value = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addArticle(@RequestBody ArticleDTO articleDTO) {
        this.articleService.createArticle(articleDTO);
    }

    @GetMapping(value = "/count")
    public Map<Date, Integer> getArticleList() {
        return this.articleService.countOfArticlesByLastSevenDays();
    }
}
