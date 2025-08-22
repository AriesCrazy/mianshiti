package com.beiyou.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderingController {


    @GetMapping("/test")
    public String test( )  {

        return "test";
    }

    @GetMapping("/test2")
    public String test2( )  {

        return "test";
    }
}
