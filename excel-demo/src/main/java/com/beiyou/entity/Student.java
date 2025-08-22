package com.beiyou.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private String name;

    private Integer age;

    private String sex;

    private String address;

    private String phone;

    private String email;
}
