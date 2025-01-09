package com.tutomato.climbinggymapi.common.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Jwt {

    private String accessToken;
    private String refreshToken;




}
