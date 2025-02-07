package com.tutomato.api.member.domain;

import com.tutomato.api.gym.domain.Gym;
import com.tutomato.api.membership.domain.Membership;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.internal.util.stereotypes.Lazy;

import java.util.List;

@Entity
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Membership> membershipList;
}
