package com.beiyou.event;

import org.springframework.context.ApplicationEvent;

//定义一个事件，这个就是 喂鱼的事件
public class FeedFishEvent  extends ApplicationEvent {

    public FeedFishEvent(Object source) {
        super(source);
    }
}
