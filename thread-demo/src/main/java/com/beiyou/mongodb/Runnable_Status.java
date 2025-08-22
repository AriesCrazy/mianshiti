package com.beiyou.mongodb;

import cn.hutool.core.thread.ThreadUtil;

public class Runnable_Status {

    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            ThreadUtil.safeSleep(2000);
            System.out.println("运行了");
        });
        t1.start();

        System.out.println("t1:"+t1.getState());
         //RUNNING     ABLE
    }

}
