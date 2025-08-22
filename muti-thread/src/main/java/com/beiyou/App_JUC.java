package com.beiyou;


import cn.hutool.core.thread.ThreadUtil;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 多线程 线程池

 */
public class App_JUC {

    private static  int count = 0;
    private static AtomicInteger count2 = new AtomicInteger(0);
    private static AtomicLong count3 = new AtomicLong(0);
    public static void main(String[] args) throws InterruptedException {

        for (int i = 0;i< 10000;i++){

            Thread thread = new Thread(()->{
                count3.incrementAndGet();
            });
            thread.start();
            //cas   compareAndSwap
        }

       ThreadUtil.safeSleep(5*1000);

        System.out.println("最终结果"+ count3);


    }

}
