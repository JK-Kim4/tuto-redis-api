package com.tutomato.common.exception;

import lombok.Getter;

@Getter
public class UnsatisfyingParameterException extends RuntimeException {

    private final String parameterName;
    private final String message;

    public UnsatisfyingParameterException(String message){
        this.parameterName = null;
        this.message = message;
    }

    public UnsatisfyingParameterException(String parameterName, String message) {
        this.parameterName = parameterName;
        this.message = message;
    }

}
