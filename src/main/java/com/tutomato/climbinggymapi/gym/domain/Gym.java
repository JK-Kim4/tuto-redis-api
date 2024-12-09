package com.tutomato.climbinggymapi.gym.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalTime;

@Entity @Getter
public class Gym {

    @Id @Column(name = "gym_id")
    private Long id;

    private String name;

    private String location;

    private String address;

    private String phoneNumber;

    private LocalTime openTime;

    private LocalTime closeTime;
}
