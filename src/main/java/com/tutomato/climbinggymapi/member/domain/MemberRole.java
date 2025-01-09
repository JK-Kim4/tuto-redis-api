package com.tutomato.climbinggymapi.member.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class MemberRole {

    @Id @Column(name = "member_role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberRoleId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private Role role;

    @Builder
    public MemberRole(Member member ,Role role){
        this.member = member;
        this.role = role;
    }

}
