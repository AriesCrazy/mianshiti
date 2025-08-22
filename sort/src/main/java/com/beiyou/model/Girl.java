package com.beiyou.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.locks.Lock;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Girl {
    private Integer id;
    private Integer age;
    private String name;

}





