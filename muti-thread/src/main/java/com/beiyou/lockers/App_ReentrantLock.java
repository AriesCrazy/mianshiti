package com.beiyou.lockers;


import cn.hutool.core.thread.ThreadUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock  可重入锁
 */
public class App_ReentrantLock {
    static ReentrantLock lock = new ReentrantLock(true);
    public static void main(String[] args) throws InterruptedException, IOException {
        // lock 与  synchronized 的区别
        // 共同点： 1. 重入锁，可以重复获取锁
        // 不同点： 1. lock 可以实现公平和非公平
        //          2. 实现lock的锁必须自己 上锁和释放锁， 粒度可控，比较灵活.synchronized 会自动上锁和释放锁，。
        //          3. lock 都有 tryLock 尝试锁，不会出现死锁
        Thread t1 = new Thread(() -> {

            System.out.println(Thread.currentThread().getId() + "-t1获取到锁");
            ThreadUtil.safeSleep(5000);
            test1();
            lock.unlock();
            System.out.println(Thread.currentThread().getId() + "-t1释放锁");
        });
        Thread t2 = new Thread(() -> {
            lock.lock();
            System.out.println(Thread.currentThread().getId() + "-t2获取到锁");
            ThreadUtil.safeSleep(5000);
            lock.unlock();
            System.out.println(Thread.currentThread().getId() + "-t2释放锁");
        });
        t1.start();
        t2.start();

        System.in.read();
    }

    public  static   void test1(){
        lock.lock();
        System.out.println(Thread.currentThread().getId() + "-t1-test1获取到锁");
        ThreadUtil.safeSleep(8000);
        test2();
        lock.unlock();
        System.out.println(Thread.currentThread().getId() + "-t1-test1释放锁");
    }
    public  static   void test2(){
        lock.lock();
        System.out.println(Thread.currentThread().getId() + "-t1-test2获取到锁");
        ThreadUtil.safeSleep(5000);
        lock.unlock();
        System.out.println(Thread.currentThread().getId() + "-t1-test2释放锁");
    }
}
