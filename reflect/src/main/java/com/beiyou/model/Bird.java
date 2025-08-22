package com.beiyou.model;



import com.beiyou.annotation.DbType;
import lombok.Data;

import java.util.concurrent.CountDownLatch;


@Data
@DbType(value = "redis",type = 108)
public class Bird  {

    public static  String address = "郑州";

    private String  name;
    private Integer age;

    //如果不写构造函数，系统会默认生成一个无参构造函数
    // 如果你写了构造，系统不再帮我们生成构造函数
    public Bird(){

    }
    public Bird(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String fly(){
        System.out.println("我"+ this.age +   "岁了，我能飞了");
        return   "我"+ this.age +   "岁了，我能飞了";
    }
    public void fly2(String color,Integer height){
        System.out.println("我fly2" + this.name + this.age + color + height+   "了，我能飞了");
    }

    public static String staticFly3(String mm,Integer age){
        System.out.println("我"+ mm +   "了，我能飞了");
        return   "我"+ mm +   "了，我能飞了";
    }

}
