package com.tutomato.climbinggymapi.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false, unique = true)
    private String password;

    private String nickname;

    private String gymName;

    @Column(length = 512)
    private String desc;

    private String refreshToken;

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

//    @OneToMany(mappedBy = "member",cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
//    private Set<MemberRole> memberRoles = new HashSet<>();

    //for test
    public Member(String nickname, String gymName, String desc) {
        this.nickname = nickname;
        this.gymName = gymName;
        this.desc = desc;
    }

    @Builder
    public Member(String email, String password, String nickname, String gymName, String desc) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.gymName = gymName;
        this.desc = desc;
    }

}
