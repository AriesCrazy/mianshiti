package com.beiyou.mongodb;

import cn.hutool.core.thread.ThreadUtil;

import java.io.IOException;

public class Waiting_Status {
    private static final Object locker = new Object();
    public static void main(String[] args) throws IOException {
        Thread t1 = new Thread(() -> {

          System.out.println("t1 start");
            synchronized (locker) {
                try {
                    locker.wait(); //用于在多线程环境中使当前线程进入WAITING状态，并释放锁,一定要在同步代码块中使用
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            System.out.println("t1 end");

        });
        Thread t2 = new Thread(() -> {
            synchronized (locker) {
                System.out.println("t2 start");
                try {
                    locker.wait(); //用于在多线程环境中使当前线程进入WAITING状态，并释放锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t2 end");
            }
        });
        Thread t3 = new Thread(() -> {
            synchronized (locker) {
                System.out.println("t3 start");
                ThreadUtil.safeSleep(8*1000);
               // locker.notify(); // 唤醒一个等待线程
                locker.notifyAll();// 唤醒所有等待线程
                System.out.println("t3 end");
            }
        });
        t1.start();
        t2.start();
        ThreadUtil.safeSleep(1000);
        t3.start();
        while (true){
            System.out.println("t1 -> "+ t1.getState());
            System.out.println("t2 -> "+ t2.getState());
            System.out.println("t3 -> "+ t3.getState());
            ThreadUtil.safeSleep(1000);
        }

    }

}
