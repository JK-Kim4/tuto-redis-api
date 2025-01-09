package com.tutomato.climbinggymapi.member.api.dto;

import com.tutomato.climbinggymapi.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberSaveDto {

    private String email;
    private String password;
    private String nickname;
    private String desc;

    public Member toEntity(){
        return Member.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .desc(desc)
                .build();
    }


}
