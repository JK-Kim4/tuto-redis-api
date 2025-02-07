package com.tutomato.api.member.domain;

import com.tutomato.api.gym.domain.Gym;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "contract")
    private String contract;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gym_id")
    private Gym gyms;
}
