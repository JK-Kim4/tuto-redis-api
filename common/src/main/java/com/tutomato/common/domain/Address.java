package com.tutomato.common.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable @Getter
@NoArgsConstructor
public class Address {

    private String zipCode;
    private String city;
    private String country;
    private String street;
    private String streetNumber;
    private String state;

    @Builder
    public Address(String zipCode, String city, String country, String street, String streetNumber, String state) {
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
        this.street = street;
        this.streetNumber = streetNumber;
        this.state = state;
    }

}
