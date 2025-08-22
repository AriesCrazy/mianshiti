package com.beiyou.juc;

import cn.hutool.core.date.StopWatch;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

//CountDownLatch 是 Java 中的一个并发工具类，它的作用是允许一个或多个线程等待其他线程执行完毕后再继续执行。
//具体来说，CountDownLatch 维护了一个计数器，初始化时需要指定计数器的初始值。当某个线程调用 CountDownLatch 的 await() 方法时，如果计数器的值不为 0，那么该线程会被阻塞，直到计数器的值为 0。而其他线程执行完任务后，会调用 CountDownLatch 的 countDown() 方法来减少计数器的值，直到计数器的值为 0，所有等待的线程才会被唤醒继续执行。
//CountDownLatch 可以用于一些场景，例如主线程等待多个子线程执行完毕后再继续执行，或者多个线程等待某个共享资源的初始化完成后再开始执行等等。
@Slf4j
public class CountDownLatch_App {

    public static void main(String[] args) throws InterruptedException, IOException {
        CountDownLatch latch = new CountDownLatch(4);
        for (int i = 1; i <= 4; i++) {
            new Thread(() -> {
                //模拟客户 化妆时间
                double time = RandomUtil.randomDouble(3, 20);
                ThreadUtil.safeSleep(time * 1000);
                log.debug("我来了,{}, 准备了{}秒", Thread.currentThread().getName(), time);
                latch.countDown(); // 调此方法 CountDownLatch 计数器会-1

            }, "人员" + i).start();
        }

        log.debug("司机开始等待.");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        // 司机线程会阻塞，直到CountDownLatch的计数器为0
        latch.await();
        stopWatch.stop();
        log.debug("所有人员已准备好，出发， 耗时：{}", stopWatch.getTotalTimeSeconds());
        System.in.read();
    }

}
