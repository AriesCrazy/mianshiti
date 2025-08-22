package com.beiyou.listener;

import org.springframework.boot.ImageBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

//实现了ApplicationListener，代表他是一个监听器
// ApplicationStartingEvent 代表我监听的是生命周期中的哪个事件
public class MyEventListener2 implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        System.out.println("触发了ApplicationStartedEvent");
    }
}
