package com.tutomato.common.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException{

    private String errorMessage;

    public UserNotFoundException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
