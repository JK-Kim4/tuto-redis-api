package com.tutomato.api.member.api.dto;

import com.tutomato.api.member.domain.Member;
import lombok.*;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberSaveDto {

    private Long gymId;
    private String nickname;
    private String contract;

    @Builder
    public MemberSaveDto(Long gymId, String nickname, String contract) {
        this.gymId = gymId;
        this.nickname = nickname;
        this.contract = contract;
    }

    public Member toMember(){
        return Member.builder()
                .nickname(this.getNickname())
                .contract(this.getContract())
                .build();
    }

}
