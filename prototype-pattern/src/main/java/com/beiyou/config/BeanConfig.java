package com.beiyou.config;

import com.beiyou.model.Address;
import com.beiyou.model.Boy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Configuration
public class BeanConfig {

    @Bean // 默认是单例的
    @Scope(scopeName="prototype") //原型
    // 每次调用的时候都会根据模版创建一个新的实例对象
    public Boy boy(){
        Boy boy = new Boy("文斌",18);
        boy.setAddress(new Address("洛阳","十字街88号"));
        return boy;
    }

    //深copy 和 浅 copy

}
