package com.tutomato.common.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Brand extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String identifier;

    @Embedded
    private Address address;

    private String contact;

    public Brand(String name, String description,String identifier, Address address, String contact) {
        this.name = name;
        this.description = description;
        this.identifier = identifier;
        this.address = address;
        this.contact = contact;
    }



}
