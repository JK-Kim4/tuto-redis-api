package com.tutomato.coreapi.jwt.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


/**
 * JwtRule
 * JWT 관련한 상수(헤더값, 토큰, 리프레쉬토큰)에 대한 정보
 * */
@Getter
@RequiredArgsConstructor
public enum JwtRule {

    JWT_ISSUE_HEADER("Set-Cookie"),
    JWT_RESOLVE_HEADER("Cookie"),
    ACCESS_PREFIX("access"),
    REFRESH_PREFIX("refresh");

    private final String value;
}
