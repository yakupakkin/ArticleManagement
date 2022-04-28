package com.article.management.dao;

import com.article.management.model.Article;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
@Repository
public interface IArticleRepository extends CrudRepository<Article, Integer> {
    /*@Query("select publishingDate,count(*) from article where publishingDate:=")
    Map<Date,Integer> countOfArticlesByLastSevenDays();
    @Query("FROM Article a WHERE a.publishingDate between :startDate and :endDate group by a.publishingDate")
    List<Article> findAllByPublishingDateBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
*/
    List<Article> findAllByPublishingDateBetween( LocalDateTime startDate, LocalDateTime endDate);

}
