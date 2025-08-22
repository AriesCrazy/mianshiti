package com.example;

import cn.hutool.core.thread.ThreadUtil;
import com.example.model.Member;

import java.util.HashMap;

public class ThreadLocalUtil {



    private static  ThreadLocal<Member> threadLocal = new ThreadLocal<>();

    private Member member;

    public static void set(Member member) {
        threadLocal.set(member);
    }

    public static Member get() {
        return threadLocal.get();
    }

    public void remove() {
        threadLocal.remove();
    }

    public static void clear() {
        threadLocal = null;
    }
}