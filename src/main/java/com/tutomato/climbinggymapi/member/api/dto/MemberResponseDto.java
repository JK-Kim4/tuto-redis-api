package com.tutomato.climbinggymapi.member.api.dto;

import com.tutomato.climbinggymapi.member.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberResponseDto {

    private String email;
    private String nickname;

    public MemberResponseDto(Member member) {
        this.email = member.getEmail();
        this.nickname = member.getNickname();
    }
}
