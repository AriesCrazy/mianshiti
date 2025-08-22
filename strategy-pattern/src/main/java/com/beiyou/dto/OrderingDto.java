package com.beiyou.dto;

import lombok.Data;

@Data
public class OrderingDto {
    private Integer memberId;
    private String orderType; //团购,秒杀，正常
    private String[] products;
}
