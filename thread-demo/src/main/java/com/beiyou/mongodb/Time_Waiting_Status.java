package com.beiyou.mongodb;

import cn.hutool.core.thread.ThreadUtil;

import java.io.IOException;

public class Time_Waiting_Status {

    public static void main(String[] args) throws IOException {

       Thread t1  = new Thread(()->{
           System.out.println("子线程 运行了");

           ThreadUtil.safeSleep(10*1000);
           System.out.println("子线程 醒了");
       });
        t1.start();

        while (t1.getState() != Thread.State.TERMINATED){
            System.out.println("t1 -> "+ t1.getState());
            ThreadUtil.safeSleep(1000);

        }
        System.out.println("t1 -> "+ t1.getState());
       System.in.read();
    }

}
