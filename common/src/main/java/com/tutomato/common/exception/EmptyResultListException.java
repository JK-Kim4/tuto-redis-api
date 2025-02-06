package com.tutomato.common.exception;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmptyResultListException extends RuntimeException {

    private String errorMessage;

    public EmptyResultListException(){
        this.errorMessage = "Empty result list";
    }

    public EmptyResultListException(String message) {
        this.errorMessage = message;
    }

}
