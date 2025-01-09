package com.tutomato.climbinggymapi.member.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthenticateMember {

    private String email;
    private Set<Role> roles;

    public AuthenticateMember(String email, Set<Role> roles) {
        this.email = email;
        this.roles = roles;
    }
}
