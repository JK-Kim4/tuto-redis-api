package com.tutomato.climbinggymapi.member.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberLoginDto {

    private String email;
    private String password;

}
