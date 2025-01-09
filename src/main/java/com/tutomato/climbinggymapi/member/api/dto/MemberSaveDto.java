package com.tutomato.climbinggymapi.member.api.dto;

import com.tutomato.climbinggymapi.member.domain.Member;
import lombok.*;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberSaveDto {

    private String email;
    private String password;
    private String nickname;
    private String desc;

    @Builder
    public MemberSaveDto(String email, String password, String nickname, String desc) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.desc = desc;
    }

    public Member toEntity(){
        return Member.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .desc(desc)
                .build();
    }


}
