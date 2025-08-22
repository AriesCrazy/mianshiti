package com.beiyou.controller;


import com.beiyou.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @GetMapping("/user")
    public String test( )  {

        return "test";
    }

    @GetMapping("/user2")
    public String test2( )  {

        return "test";
    }

    @PostMapping("/user2")
    public String test3(@RequestBody User user)  {

        return "test";
    }
}
