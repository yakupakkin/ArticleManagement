package com.article.management.dao;

import com.article.management.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface IArticleRepository extends JpaRepository<Article, Long> {
    @Query(value = "SELECT * FROM Article WHERE PUBLISHINGDATE >:startDate", nativeQuery = true)
    List<Article> findArticlesLastSevenDays(@Param("startDate") String startDate);

}
