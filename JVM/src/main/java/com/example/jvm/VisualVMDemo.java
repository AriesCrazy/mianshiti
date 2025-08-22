package com.example.jvm;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.json.JSONConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;


public class VisualVMDemo {


    private static final Object locker1 = new Object();
    private static final Object locker2 = new Object();
    private static List<byte[]> list = new ArrayList<>();    // 创建一个大数组以模拟内存使用

    public static void main(String[] args) throws InterruptedException {

        // 创建线程以模拟CPU使用和线程活动
        Thread yangmi = new Thread(() -> {

                // 模拟CPU密集型任务
              while(true){
                  //System.out.println("yangmi is running");
                  int m =0;
              }

        },"yangmi");

        // 创建线程以模拟线程死锁
        Thread dead1 = new Thread(() -> {
            synchronized (locker1) {
                System.out.println("Thread 1 获取到锁1");
               ThreadUtil.safeSleep(500);
                synchronized (locker2) {
                    System.out.println("Thread 1 获取到锁2");
                }
            }
        },"Dead1");

        Thread dead2 = new Thread(() -> {
            synchronized (locker2) {
                System.out.println("Thread 2 获取到锁2");
                ThreadUtil.safeSleep(500);
                synchronized (locker1) {
                    System.out.println("Thread 2 获取到锁1");
                }
            }
        },"Dead2");

        // 启动线程
        yangmi.start();
        dead1.start();
        dead2.start();

        // 模拟内存泄漏
        while (true) {
            // 持续创建对象但不释放引用
            list.add(new byte[1024 * 1024]); // 添加1MB数据
            ThreadUtil.safeSleep(1000);
        }
    }
}


