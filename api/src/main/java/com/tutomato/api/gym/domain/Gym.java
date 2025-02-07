package com.tutomato.api.gym.domain;

import com.tutomato.api.member.domain.Member;
import com.tutomato.common.domain.Address;
import com.tutomato.common.domain.Brand;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Gym extends Brand{

    @Enumerated(EnumType.STRING)
    private GymType gymType;

    @OneToMany(mappedBy = "gym")
    private List<Member> memberList;

    @Builder
    public Gym(String name, String description, String contact, Address address, GymType gymType) {
        super(name, description, address, contact);
        this.gymType = gymType;
    }
}
