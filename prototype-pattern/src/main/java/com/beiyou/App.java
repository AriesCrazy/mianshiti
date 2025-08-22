package com.beiyou;

import cn.hutool.core.util.ArrayUtil;
import com.beiyou.model.Address;
import com.beiyou.model.Boy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class App {

    public static void main(String[] args) throws CloneNotSupportedException {

        ConfigurableApplicationContext ctx = SpringApplication.run(App.class, args);
        Boy boy = new Boy("文斌",18);
        boy.setAddress(new Address("洛阳","十字街88号"));

        System.out.println(boy);
        System.out.println(boy.getAddress());

        Boy boy2 = (Boy)(boy.clone());

        System.out.println(boy2);
        System.out.println(boy2.getAddress());

    }}
