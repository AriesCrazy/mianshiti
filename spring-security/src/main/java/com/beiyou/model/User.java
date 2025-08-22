package com.beiyou.model;

import lombok.Data;

import javax.annotation.sql.DataSourceDefinitions;

@Data
public class User {
    private String name;
    private String tel;
}
