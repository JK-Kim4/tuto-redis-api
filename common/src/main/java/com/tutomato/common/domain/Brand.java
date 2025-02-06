package com.tutomato.common.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Brand {

    private Long id;
    private String name;
    private String description;
    private Address address;
    private String contact;

}
