package com.beiyou.controller;


import com.beiyou.event.FeedFishEvent;
import com.beiyou.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedFishController {

    @Autowired
    private ApplicationEventPublisher  applicationEventPublisher;
    //发布器

    @GetMapping("/feedfish")
    public void feedFish(String  name ){

        Student student =   Student.builder().name(name).build();
        FeedFishEvent feedFishEvent  = new FeedFishEvent(student);
        System.out.println("发布 - 喂猫事件");
        applicationEventPublisher.publishEvent(feedFishEvent);
    }
}
