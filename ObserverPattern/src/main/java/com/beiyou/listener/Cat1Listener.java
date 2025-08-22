package com.beiyou.listener;

import com.beiyou.event.FeedFishEvent;
import org.springframework.context.ApplicationListener;


//观察者1
public class Cat1Listener implements ApplicationListener<FeedFishEvent> {

    @Override
    public void onApplicationEvent(FeedFishEvent event) {
        System.out.println("Cat1触发了FeedFishEvent");
    }
}
