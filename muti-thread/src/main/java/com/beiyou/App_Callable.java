package com.beiyou;


import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 多线程  runable 与 callable 区别
 * 1. 能否获取返回值
 * 2. Callable可以获取到异常
 */
public class App_Callable {

    public static  void main(String[] args) throws ExecutionException, InterruptedException {
      //  SynchronizedDemo.test2();
        //Callable用法
        Callable<String> callable = () -> {
            System.out.println("子线程Callable 在执行,子线程名:" + Thread.currentThread().getName());
            ThreadUtil.safeSleep(10*1000);
             int c  = 5/0;
            return "这是callable结果";
        };
        FutureTask<String> futureTask = new FutureTask<>(callable);
        Thread thread2 = new Thread(futureTask);
        thread2.start();

        //通过FutureTask 获取到执行结果
        //在等待获取子线程结果的时候，如果子线程没干完，会一直阻塞
      // System.out.println("主线程走到这了");
      //  String value = futureTask.get();
      //  System.out.println("callable执行结果："+value);
        while (true){
            ThreadUtil.safeSleep(2*1000);
            System.out.println("主线程走到这了");
            System.out.println(StrUtil.format("主线程 获取到Callable结果为:{}",futureTask.get()));
        }


    }




}
