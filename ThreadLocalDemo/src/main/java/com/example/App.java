package com.example;

import cn.hutool.core.thread.ThreadUtil;
import com.example.model.Member;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;


public class App {

    public static void main(String[] args) {
        Member 张三 = Member.builder().memberId(1).name("张三").build();

        Thread t1 = new Thread(() -> {
            ThreadLocalUtil.set(new Member(1, "张三", new byte[50*1024*1024]));
            while (true) {
                ThreadUtil.safeSleep(3 * 1000);
                System.out.println("t1-> " + ThreadLocalUtil.get().getName());
            }
        });
        Thread t2 = new Thread(() -> {
            ThreadLocalUtil.set(new Member(2, "李四", new byte[50*1024*1024]));
            while (true) {
                ThreadUtil.safeSleep(3 * 1000);
                System.out.println("t2-> " + ThreadLocalUtil.get().getName());
            }

        });

        t1.start();
        t2.start();
        int i = 0;
        while (true) {
            System.out.println("主程序在运行:" + i);
            ThreadUtil.safeSleep(3 * 1000);
            i++;
            if (i == 20) {
                ThreadLocalUtil.clear();
            }
        }


    }

}
