package com.beiyou.mongodb;

import cn.hutool.core.thread.ExecutorBuilder;
import cn.hutool.core.thread.ThreadUtil;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 一个线程在某一个时间点处于一个状态，这些状态不反应操作系统的线程状态。
 * 线程状态
 * NEW         新创建的线程，尚未启动的线程状态,新创建了一个线程对象，但还没有调用start()方法。
 * RUNNABLE     在Jvm虚拟机中执行的线程所处的状态
 * BLOCKED      被阻塞等待监视器锁定的线程处于此的状态
 * WAITING      正在等待另外一个线程执行特定动作的线程所处的状态
 * TIME_WAITING  同上，它有超时时间
 * TERMINATED   已退出的线程处于此状态,终止
 */
public class App {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        // 如何使用
        System.out.println("老大: " + Thread.currentThread().getId());

        ExecutorService service  = Executors.newScheduledThreadPool(3);
        for (int i = 0; i < 100; i++) {
            Future<Long> submit = service.submit(() -> {
                ThreadUtil.safeSleep(500);
                System.out.println("小弟 " + Thread.currentThread().getId() + " 干完活了");
                return Thread.currentThread().getId();
            });

          //  Object ret = submit.get();

        }

//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//
//        Future<Integer> future = executorService.submit(new MyCallable()); //计划任务让小弟干活
//
//        Integer integer = future.get(); //get 要获取小弟任务的结果. 在小弟没给结果之前如果老大调用了get方法，老大就会一直阻塞
//
//        System.out.println("老大结束了: " + Thread.currentThread().getId());
        System.in.read();
    }

    static class MyCallable implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            ThreadUtil.safeSleep(10*1000);
            System.out.println("小弟 MyCallable: " + Thread.currentThread().getId());
            return new Random().nextInt();

        }

        static class MyRunnable implements Runnable {
            @Override
            public void run() {
                System.out.println("son: " + Thread.currentThread().getId());
                System.out.println("通过实现 Runnable 方式实现线程");
            }
        }

        static class MyThread extends Thread {
            @Override
            public void run() {
                System.out.println("son: " + Thread.currentThread().getId());
                System.out.println("通过集成 Thread 类实现线程");
            }
        }


    }
}