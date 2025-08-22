package com.beiyou.controller;


import com.beiyou.dto.OrderingDto;

import com.beiyou.serviceimpl.OrderServiceAllInOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderingController {

    @Autowired
    OrderServiceAllInOne  orderServiceAllInOne;
    @PostMapping("/api/ordering")
    public String ordering(@RequestBody OrderingDto dto){


        return orderServiceAllInOne.process(dto);
    }


}
