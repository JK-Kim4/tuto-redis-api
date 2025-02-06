package com.tutomato.common.exception;

import com.tutomato.common.util.CommonValues;
import lombok.Getter;

@Getter
public class ErrorResponse {

    private String errorMessage;
    private String errorCode;

    public ErrorResponse(){
        this.errorCode = CommonValues.ERROR_CODE_SYSTEM_ERROR;
        this.errorMessage = CommonValues.ERROR_MESSAGE_SYSTEM_ERROR;
    }

    public ErrorResponse(String errorMessage, String errorCode) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }


}
