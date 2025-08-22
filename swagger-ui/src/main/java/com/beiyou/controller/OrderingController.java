package com.beiyou.controller;



import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@Api( tags = "订单接口" )
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
