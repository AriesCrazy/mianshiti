package com.beiyou;


import cn.hutool.core.thread.ExecutorBuilder;
import cn.hutool.core.thread.ThreadUtil;

import java.io.FileNotFoundException;
import java.util.concurrent.*;

/**
 * 多线程 线程池

 */
public class App_ThreadPoll {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
         //21亿个任务


        ExecutorService executorService = Executors.newScheduledThreadPool(3);
        executorService.shutdown();
        executorService.shutdownNow();
        Future<String> future = executorService.submit(() -> "nihao");
        String s = future.get();
        Executors.newFixedThreadPool(3);
        Executors.newScheduledThreadPool(3);
        Executors.newSingleThreadExecutor();

        ExecutorService executor = ExecutorBuilder.create()
                .setCorePoolSize(5)
                .setMaxPoolSize(10)
                .setWorkQueue(new LinkedBlockingQueue<>(100))
                .setKeepAliveTime(10)
                .build();



    }

}
