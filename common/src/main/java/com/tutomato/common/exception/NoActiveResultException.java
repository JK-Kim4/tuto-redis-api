package com.tutomato.common.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoActiveResultException extends RuntimeException {

    private Class clazz;

    private String errorMessage;

    public NoActiveResultException(Class clazz, String errorMessage) {
        this.clazz = clazz;
        this.errorMessage = errorMessage;
    }

}
