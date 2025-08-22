package com.beiyou.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Address implements Serializable {
    private String city;
    private String  detail;
    public Address() {

    }
    public Address(String city, String detail) {
        this.city = city;
        this.detail = detail;
    }

}
