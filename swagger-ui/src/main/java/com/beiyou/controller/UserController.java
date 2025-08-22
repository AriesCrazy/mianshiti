package com.beiyou.controller;


import com.beiyou.model.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Api( tags = "用户接口" )
public class UserController {

    @GetMapping
    @ApiOperation(value = "查询用户",tags = "对用户的查询")
    public String test( )  {
        return "test";
    }

    @PutMapping
    @ApiOperation(value = "修改用户",tags = "这是查询的标签")
    public String put(@RequestBody User user)  {

        return "test";
    }
    @PostMapping
    @ApiOperation(value = "新增用户")
    public String insert(@RequestBody User user)  {

        return "test";
    }
    @DeleteMapping
    @ApiOperation(value = "删除用户")
    public String delete(Integer id)  {

        return "test";
    }
}
