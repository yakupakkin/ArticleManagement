package com.article.management.service;

import com.article.management.dao.IArticleRepository;
import com.article.management.dto.ArticleDTO;
import com.article.management.mapper.ArticleMapper;
import com.article.management.model.Article;
import com.article.management.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ArticleServiceImpl implements IArticleService{

    @Autowired
    IArticleRepository articleRepository;
    @Override
    public void createArticle(ArticleDTO articleDTO) {
        articleRepository.save(ArticleMapper.INSTANCE.dtoToEntity(articleDTO));
    }

    @Override
    @Transactional
    public Page<Article> findAll(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public ArticleDTO findOne(Long id) {
        return ArticleMapper.INSTANCE.entityToDTO(articleRepository.findById(id).get());
    }
    @Override
    public Map<String, Integer> countOfArticlesByLastSevenDays() {
        String startDate= DateUtil.convertLocalDateTimeToISO8601(LocalDateTime.now().minusDays(7));

        List<Article> result = articleRepository.findArticlesLastSevenDays(DateUtil.convertISO8601ToStringDate(startDate));

        Map<String,Integer>  map = new HashMap<>();

        if (!result.isEmpty()) {
            for (Article article: result) {
                String publishingDate= DateUtil.convertISO8601ToStringDate(article.getPublishingDate());
                if (map.containsKey(publishingDate)) {
                    int count = map.get(publishingDate).intValue() + 1;
                    map.put(publishingDate, count);
                } else {
                    map.put(publishingDate, 1);
                }
            }
            return map;
        } else {
            return new HashMap<String,Integer>();
        }
    }
}
