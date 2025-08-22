package com.beiyou.listener;

import com.beiyou.event.FeedFishEvent;
import org.springframework.context.ApplicationListener;

//观察者2
public class Cat2Listener implements ApplicationListener<FeedFishEvent> {

    @Override
    public void onApplicationEvent(FeedFishEvent event) {
        System.out.println("Cat2触发了FeedFishEvent");
    }
}
