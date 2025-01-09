package com.tutomato.climbinggymapi.common.exception;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberNotFoundException extends RuntimeException{

    private String errorMessage;

    public MemberNotFoundException(){
        this.errorMessage = "Member not found";
    }

    public MemberNotFoundException(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
