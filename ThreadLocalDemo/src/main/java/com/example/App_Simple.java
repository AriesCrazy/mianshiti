package com.example;


import cn.hutool.core.thread.ThreadUtil;
import com.example.model.Member;

import java.io.IOException;

public class App_Simple {

    public static void main(String[] args) throws IOException {
        ThreadUtil.safeSleep(5 * 1000);
        task();
        System.gc();


        System.in.read();
    }

    private static void task() {
          ThreadLocal<Member> local = new ThreadLocal<>();
          local.set(new Member(1, "张三", new byte[100*1024*1024]));
         ThreadUtil.safeSleep( 10 * 1000);
         local.remove();
    }
}
