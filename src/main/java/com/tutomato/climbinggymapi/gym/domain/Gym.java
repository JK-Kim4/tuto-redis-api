package com.tutomato.climbinggymapi.gym.domain;

import com.tutomato.climbinggymapi.gym.domain.dto.GymSaveDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Gym {

    @Id @Column(name = "gym_id")
    private Long id;

    private String name;

    private String location;

    private String address;

    private String phoneNumber;

    private boolean isOpen;

    private LocalTime openTime;

    private LocalTime closeTime;

    public Gym(GymSaveDto dto){
        this.name = dto.getName();
        this.location = dto.getLocation();
        this.address = dto.getAddress();
        this.phoneNumber = dto.getPhoneNumber();
        this.isOpen = dto.isOpen();
        this.openTime = dto.getOpenTime();
        this.closeTime = dto.getCloseTime();
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
            boolean isOpen){
        this.isOpen = isOpen;
    }

    public void updateOperationTime(
            LocalTime openTime, LocalTime closeTime){
        this.openTime = openTime;
        this.closeTime = closeTime;
    }
}
