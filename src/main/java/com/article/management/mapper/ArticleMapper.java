package com.article.management.mapper;

import com.article.management.dto.ArticleDTO;
import com.article.management.model.Article;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ArticleMapper {

    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

    ArticleDTO entityToDTO(Article article);
    Article dtoToEntity(ArticleDTO articleDTO);
    List<ArticleDTO> mapToDTOList(List<Article> articles);
    List<Article> mapToEntityList(List<ArticleDTO> articleDTOs);
}
