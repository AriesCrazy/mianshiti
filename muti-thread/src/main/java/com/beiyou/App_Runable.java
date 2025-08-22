package com.beiyou;


import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 多线程  runable 与 callable 区别
 * 1. 能否获取返回值
 * 2. Runable 无法获取异常
 */
public class App_Runable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //Runable用法，无法获取返回值和异常
        Thread t1 = new Thread(() -> {
            int a = 5/0;
            System.out.println("子线程Runable 在执行,子线程名:"+Thread.currentThread().getName());
        });
        t1.start();

        while (true){
            ThreadUtil.safeSleep(2*1000);
            System.out.println("主要线程名:"+Thread.currentThread().getName());
        }


    }

}
