package com.beiyou.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.annotation.sql.DataSourceDefinitions;

@Data
@ApiModel("这是用户对象")
public class User {

    @ApiModelProperty(value = "姓名",name = "姓名2",example = "张三")
    private String name;
    @ApiModelProperty(value = "手机号",name = "手机号2",example = "132756877")
    private String tel;
}
