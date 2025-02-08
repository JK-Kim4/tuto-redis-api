package com.tutomato.common.exception;

import lombok.Getter;

@Getter
public class AlreadyExistException extends RuntimeException {

    private Class clazz;
    private String errorMessage;

    public AlreadyExistException(Class clazz, String errorMessage) {
        this.clazz = clazz;
        this.errorMessage = errorMessage;
    }
}
