package com.beiyou.juc;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

//StampedLock 是 Java 8 中引入的一种读写锁的改进版本，它的作用是提供了一种乐观读的机制，以及更细粒度的控制锁的访问模式。
//StampedLock 的主要作用有以下几点：
//1. 读写分离：StampedLock 支持读写分离，多个线程可以并发读取数据，但只有一个线程可以进行写操作。这种读写分离的机制可以提高并发性能。
//2. 乐观读锁：StampedLock 提供了一种乐观读锁的机制，即 tryOptimisticRead() 方法。乐观读锁不会阻塞其他线程的写操作，当需要读取数据时，先获取一个乐观读锁的标记（stamp），然后读取数据。在读取完数据后，需要使用 validate() 方法验证标记是否仍然有效，如果有效，则读取成功，否则需要重新获取读锁。
//3. 悲观读锁和写锁：StampedLock 也支持悲观读锁和写锁，与传统的读写锁类似。悲观读锁和写锁会阻塞其他线程的读和写操作，保证数据的一致性。
//StampedLock 可以用于一些读多写少的场景，通过乐观读锁的机制可以提高并发性能，但需要注意使用时的正确性和线程安全性。
@Slf4j
public class StampedLock_App {

    private static final StampedLock lock = new StampedLock();

    public static void main(String[] args) throws InterruptedException {

        Thread z1 = new Thread(() -> {
            while (true)
            {
                chi("猪1");
                ThreadUtil.safeSleep(500);
            }
        });
        Thread z2 = new Thread(() -> {
            while (true)
            {
                chi("猪2");
                ThreadUtil.safeSleep(500);
            }
        });
        Thread z3 = new Thread(() -> {
            while (true)
            {
                chi("猪3");
                ThreadUtil.safeSleep(500);
            }
        });


        z1.start();
        z2.start();
        z3.start();

        while (true) {
            ThreadUtil.safeSleep(8000);
            new Thread(() -> {
                wei("喂");
            }).start();

        }


    }
    static void chi(String name) {
        long stamp = lock.tryOptimisticRead();
        System.out.println(name + " 吃饭 .... ");
        ThreadUtil.safeSleep(3 * 1000);
        System.out.println(name + " 吃结束 .... ");
        lock.tryUnlockRead();

    }

    static void wei(String name) {
        long stamp =  lock.writeLock();
        System.out.println(name + " 喂食 .... ");
        ThreadUtil.safeSleep(6 * 1000);
        System.out.println(name + " 喂结束 .... ");
        lock.unlockWrite(stamp);
    }

}
