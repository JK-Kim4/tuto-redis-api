package com.tutomato.api.gym.api.dto;

import com.tutomato.api.gym.domain.Gym;
import com.tutomato.api.gym.domain.GymType;
import com.tutomato.common.domain.Address;
import lombok.*;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GymSaveDto {

    private String name;
    private String description;
    private String contact;
    private String identifier;
    private String gymType;
    private String zipCode;
    private String city;
    private String country;
    private String street;
    private String streetNumber;
    private String state;

    @Builder
    public GymSaveDto(String name, String description, String identifier, String contact, String gymType, String zipCode, String city, String country, String street, String streetNumber) {
        this.name = name;
        this.description = description;
        this.contact = contact;
        this.identifier = identifier;
        this.gymType = gymType;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.street = street;
        this.streetNumber = streetNumber;

    }

    public Gym toGym() {
        return Gym.builder()
                .name(this.getName())
                .description(this.getDescription())
                .contact(this.getContact())
                .identifier(this.getIdentifier())
                .address(Address.builder()
                            .zipCode(this.getZipCode())
                            .city(this.getCity())
                            .country(this.getCountry())
                            .street(this.getStreet())
                            .streetNumber(this.getStreetNumber())
                            .state(this.getState())
                        .build())
                .gymType(GymType.valueOf(this.getGymType()))
                .build();
    }

}
