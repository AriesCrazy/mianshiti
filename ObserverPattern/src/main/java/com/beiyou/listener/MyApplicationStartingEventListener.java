package com.beiyou.listener;

import org.springframework.boot.Banner;
import org.springframework.boot.ImageBanner;
import org.springframework.boot.ResourceBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.PrintStream;

//实现了ApplicationListener，代表他是一个监听器
// ApplicationStartingEvent 代表我监听的是生命周期中的哪个事件
public class MyApplicationStartingEventListener implements ApplicationListener<ApplicationStartingEvent> {
    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        System.out.println("触发了ApplicationStartingEvent");
        SpringApplication springApplication = event.getSpringApplication();
        //关闭banner
        //springApplication.setBannerMode(Banner.Mode.OFF);
        Resource resource = new ClassPathResource("b.png");
        ImageBanner  banner = new ImageBanner(resource) ;
       // ResourceBanner resource1 = new ResourceBanner(resource);
        springApplication.setBanner(banner);

    }
}
