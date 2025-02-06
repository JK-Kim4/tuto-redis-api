package com.tutomato.api.gym.domain;

import com.tutomato.common.domain.Address;
import com.tutomato.common.domain.Brand;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Gym extends Brand {

    private GymType gymType;


    @Builder
    public Gym(String name, String description, String contact, Address address, GymType gymType) {
        super(name, description, address, contact);
        this.gymType = gymType;
    }
}
