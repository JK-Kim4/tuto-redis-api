package com.tutomato.climbinggymapi.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 회원 정보 Entity
 *
 * */
@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "identifier")
    private String identifier;  //JWT 식별자

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;       // JWT 회원 이메일

    @Column(name = "password", length = 300, nullable = false)
    private String password;    // JWT 회원 패스워드

    private String nickName;

    private String gymName;

    @Column(length = 512)
    private String desc;

    private Role role;

    public Member(String nickName, String gymName, String desc) {
        this.nickName = nickName;
        this.gymName = gymName;
        this.desc = desc;
    }

}
