package com.tutomato.climbinggymapi.gym.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter @Setter
public class GymSaveDto {

    private Long id;
    private String name;
    private String location;
    private String address;
    private String phoneNumber;
    private boolean isOpen;
    private LocalTime openTime;
    private LocalTime closeTime;

    @Builder
    public GymSaveDto(
            Long id, String name,
            String location, String address,
            String phoneNumber, boolean isOpen,
            LocalTime openTime, LocalTime closeTime){
        this.id = id;
        this.name = name;
        this.location = location;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.isOpen = isOpen;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }


}
