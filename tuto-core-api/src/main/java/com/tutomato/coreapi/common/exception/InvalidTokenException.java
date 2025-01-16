package com.tutomato.coreapi.common.exception;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class InvalidTokenException extends RuntimeException{

    private String errorMessage;

    public InvalidTokenException() {
        this.errorMessage = "유호하지 않은 토큰입니다.";
    }

    public InvalidTokenException(String errorMessage) {
        this.errorMessage = errorMessage;
    }


}
