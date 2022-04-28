package com.article.management.exception;

public class UserNotFoundException extends RuntimeException{

  public UserNotFoundException(String message){
      super(message);
    }
}
