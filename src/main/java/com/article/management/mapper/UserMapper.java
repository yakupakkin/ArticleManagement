package com.article.management.mapper;

import com.article.management.dto.ArticleDTO;
import com.article.management.dto.UserDTO;
import com.article.management.model.Article;
import com.article.management.model.User;
import org.mapstruct.factory.Mappers;

import java.util.List;

public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO entityToDTO(User user);
    User dtoToEntity(UserDTO articleDTO);
    List<UserDTO> mapToDTOList(List<User> users);
    List<User> mapToEntityList(List<UserDTO> articleDTOs);
}
