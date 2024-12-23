package com.tutomato.climbinggymapi.common.exception;


public class GymApiCustomException extends RuntimeException{
    public GymApiCustomException() {
        super();
    }

    public GymApiCustomException(String message) {
        super(message);
    }

    public GymApiCustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public GymApiCustomException(Throwable cause) {
        super(cause);
    }

    protected GymApiCustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
