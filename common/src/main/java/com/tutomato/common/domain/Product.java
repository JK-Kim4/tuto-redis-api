package com.tutomato.common.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Product {

    private Long id;
    private String name;
    private String description;
    private Integer price;
    private Integer stock;
    private Brand brand;

}
