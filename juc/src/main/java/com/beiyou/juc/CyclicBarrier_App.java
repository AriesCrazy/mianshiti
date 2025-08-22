package com.beiyou.juc;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.StopWatch;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

//CyclicBarrier 是 Java 中的一个并发工具类，它的作用是让多个线程在某个屏障点处等待，直到所有线程都达到屏障点后再一起继续执行。
//具体来说，CyclicBarrier 维护了一个计数器和一个屏障点。初始化 CyclicBarrier 时需要指定计数器的初始值和在达到屏障点时需要执行的动作。当某个线程调用 CyclicBarrier 的 await() 方法时，该线程会被阻塞，同时计数器的值会减少。直到计数器的值为 0，所有等待的线程才会被唤醒继续执行，并且会执行指定的动作。在所有线程继续执行后，CyclicBarrier 的计数器会被重置为初始值，可以被重复使用。
//CyclicBarrier 可以用于一些场景，例如多个线程需要等待彼此到达某个状态后再继续执行，或者多个线程需要等待某个共享资源的初始化完成后再开始执行等等。
@Slf4j
public class CyclicBarrier_App {


    public static void main(String[] args) throws InterruptedException {


        CyclicBarrier barrier = new CyclicBarrier(4);

        ArrayList<String> spots = CollUtil.newArrayList("少林寺", "清院", "黄帝故里", "康百万庄园");
        spots.forEach(spot->{
            ThreadUtil.safeSleep(5000);
            log.debug("景点{}",spot);
            new Palyer(barrier, "游客1", spot).start();
            new Palyer(barrier, "游客2",spot).start();
            new Palyer(barrier, "游客3",spot).start();
            try {
                barrier.await();
            } catch (Exception e) {
            }
            barrier.reset();
        });

    }

    public static class Palyer extends Thread {
        private String name;
        private CyclicBarrier barrier;
        private String spot;
        public Palyer(CyclicBarrier cyclicBarrier, String name,String spot) {
           this.name = name;
           this.spot = spot;
           this.barrier = cyclicBarrier;
        }
        @Override
        public void run() {
            // 游玩
            double time = RandomUtil.randomDouble(2, 9);
            ThreadUtil.safeSleep(time * 1000);
            log.debug("{} 景点{} 游玩了{}秒，到达车子",name,spot, time);
            // 线程到达屏障点
            try {
                barrier.await(); // barrier 计数器就会-1，直到 0
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
            log.debug(" 看到所有人到达车子,{}在车上出发了，去下一个景点",name);

        }
    }
}
