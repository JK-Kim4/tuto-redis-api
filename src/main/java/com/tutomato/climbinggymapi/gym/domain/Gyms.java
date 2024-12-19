package com.tutomato.climbinggymapi.gym.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class Gyms {

    private List<Gym> gyms = new ArrayList<>();

    public Gyms(List<Gym> gyms) {
        this.gyms = gyms;
    }
}
