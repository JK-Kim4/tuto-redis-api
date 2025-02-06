package com.tutomato.common.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NotUsableResultException extends RuntimeException {

    private String errorMessage;

    public NotUsableResultException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
