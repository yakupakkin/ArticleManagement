package com.article.management.service;

import com.article.management.dao.IUserRepository;
import com.article.management.dto.UserDTO;
import com.article.management.exception.DuplicateUsernameException;
import com.article.management.exception.UserNotFoundException;
import com.article.management.mapper.UserMapper;
import com.article.management.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository userRepository;


    @Override
    public void save(UserDTO userDto) {

        User user = userRepository.getUserByUsername(userDto.getUsername());
        if (user != null) {
            throw new DuplicateUsernameException("Username has already exists!!");
        }



        userRepository.save(UserMapper.INSTANCE.dtoToEntity(userDto));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }


    @Override
    public User getLoginUser() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (username.isEmpty()) {
            throw new IllegalArgumentException("Invalid User Token");
        }
        User user = userRepository.getUserByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User not found!!");
        }

        return user;
    }
}