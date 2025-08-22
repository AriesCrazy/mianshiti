package com.beiyou;


import cn.hutool.core.thread.ThreadUtil;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * 多线程  runable 与 callable 区别
 * 1. 能否获取返回值
 * 2. Runable 无法获取异常
 */
public class App_Thread {

    //面试题： start（）  和  run（） 区别？
    // start（）  创建一个新线程，并调用该线程的 run 方法；
    // run（）  只调用该线程的 run 方法，而不创建新线程。
    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {

        MyThread  myThread  = new MyThread();


       // myThread.start();
        myThread.run();

        System.out.println("主线程名:"+Thread.currentThread().getName());
        System.in.read();
    }

    static class  MyThread extends  Thread {
        @Override
        public void run() {
            System.out.println("子线程名:"+Thread.currentThread().getName());
        }
    }

}
