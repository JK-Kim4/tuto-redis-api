package com.tutomato.climbinggymapi.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalTime;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String realName;

    private String gymName;

    @Column(length = 512)
    private String desc;

    private String testString1;

    private String testString2;

    private String testString3;

    private LocalTime testTime1;

    private LocalTime testTime2;

    public Member(String realName, String gymName, String desc) {
        this.realName = realName;
        this.gymName = gymName;
        this.desc = desc;
        this.testString1 = "test1";
        this.testString2 = "test2";
        this.testString3 = "test3";
        this.testTime1 = LocalTime.now();
        this.testTime2 = LocalTime.now();
    }

}
