package com.beiyou.lockers;


import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock
 */
public class App_ReentrantReadWriteLock {

    static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    //读锁，是共享锁
    static ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
    //写锁，是独占锁
    static ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

    public static void main(String[] args) throws InterruptedException, IOException {

        Thread z1 = new Thread(() -> {
            while (true) {
                chi("猪1");
                ThreadUtil.safeSleep(1000);
            }
        });
        Thread z2 = new Thread(() -> {
            while (true) {
                chi("猪2");
                ThreadUtil.safeSleep(1000);
            }
        });
        Thread z3 = new Thread(() -> {
            while (true) {
                chi("猪3");
                ThreadUtil.safeSleep(1000);
            }
        });


        z1.start();
        z2.start();
        z3.start();

        while (true) {
            ThreadUtil.safeSleep(8000);
            new Thread(() -> {
                wei("佩奇-喂");
            }).start();

        }

    }


    static void chi(String name) {
        readLock.lock();
        System.out.println(name + " 吃饭 .... ");
        System.out.println(name + " 吃结束 .... ");
        readLock.unlock();

    }

    static void wei(String name) {
        writeLock.lock();
        System.out.println(name + " 喂食 .... ");
        ThreadUtil.safeSleep(5 * 1000);
        System.out.println(name + " 喂结束 .... ");
        writeLock.unlock();
    }
}
