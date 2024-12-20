package com.tutomato.climbinggymapi.gym.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tutomato.climbinggymapi.gym.domain.dto.GymSaveDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalTime;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Gym implements Serializable {

    @Id @GeneratedValue
    @Column(name = "gym_id")
    private Long id;

    private String name;

    private String location;

    private String address;

    private String phoneNumber;

    private Boolean isOpen;

    private LocalTime openTime;

    private LocalTime closeTime;

    public Gym(GymSaveDto dto){
        this.name = dto.getName();
        this.location = dto.getLocation();
        this.address = dto.getAddress();
        this.phoneNumber = dto.getPhoneNumber();
        //this.isOpen = dto.isOpen();
        this.isOpen = true;
        this.openTime = dto.getOpenTime();
        this.closeTime = dto.getCloseTime();
    }

    public Gym(String name){
        this.name = name;
        this.location = "test location";
        this.address = "test address";
        this.phoneNumber = "test phone number";
        this.isOpen = true;
        this.openTime = LocalTime.now();
        this.closeTime = LocalTime.now();
    }

    public void updateGymInformation(
            String name, String location,
            String address, String phoneNumber){
        this.name = name;
        this.location = location;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public void updateOpenStatus(
            boolean open){
        this.isOpen = open;
    }

    public void updateOperationTime(
            LocalTime openTime, LocalTime closeTime){
        //this.openTime = openTime;
        //this.closeTime = closeTime;
    }
}
