package com.tutomato.climbinggymapi.jwt.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Token {

    private String identifier;

    private String refreshToken;

    public Token(String identifier, String refreshToken) {
        this.identifier = identifier;
        this.refreshToken = refreshToken;
    }
}
