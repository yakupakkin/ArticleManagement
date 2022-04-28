package com.article.management.service;


import com.article.management.dto.UserDTO;
import com.article.management.model.User;

public interface IUserService {


    void save(UserDTO userDto);

    User findByUsername(String username);

    User getLoginUser();

}
