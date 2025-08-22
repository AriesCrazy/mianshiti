package com.example.jvm;

import cn.hutool.core.thread.ThreadUtil;

import java.util.ArrayList;
import java.util.List;


public class JConsoleDemo {


    private static List<byte[]> list = new ArrayList<>();

    public static void main(String[] args)  {

        // 模拟线程活动
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                // 模拟内存使用增长
                list.add(new byte[1024 * 1024]); // 添加1MB数据
                ThreadUtil.safeSleep(1000); // 模拟延迟
                System.out.println("内存添加: " + (i + 1) + "MB");

                ThreadUtil.safeSleep(1000);
            }
        });

        Thread thread2 = new Thread(() -> {
            while (true) {
                list = new ArrayList<>();
                // 模拟GC活动
                Runtime.getRuntime().gc();
                System.out.println("T2 触发了GC");
                ThreadUtil.safeSleep(1000*30);
            }
        });

        thread1.setName("T1");
        thread1.start();
        thread2.setName("T2");
        thread2.start();


    }

}
