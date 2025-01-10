package com.tutomato.climbinggymapi.jwt.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * JWT Token의 상태에 대한 정보
 *
 * */
@Getter
@RequiredArgsConstructor
public enum TokenStatus {
    AUTHENTICATED,
    EXPIRED,
    INVALID;
}
