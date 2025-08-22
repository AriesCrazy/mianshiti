package com.beiyou.enums;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Optional;

public enum KDStatus {
    APPROVE(0,"在途"),
    NEW(1,"揽收"),
    DRIVING(3,"签收");

    @Getter@Setter
    private Integer code;
    @Getter@Setter
    private String desc;
    KDStatus(Integer code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static KDStatus findByCode(Integer code){
        //jdk1.8提供了哪些新特性
        Optional<KDStatus> first = Arrays.stream(KDStatus.values()).filter(item -> item.getCode().equals(code)).findFirst();
        return first.orElse(null);
    }



}
