package com.example;

import cn.hutool.core.thread.ThreadUtil;
import com.example.model.Member;

import java.util.Arrays;


public class App2 {

  //  private static

    public static void main(String[] args) {
        ThreadLocal<MyObject> threadLocal = new ThreadLocal<>();
        // 创建一个包含大字节数据的MyObject实例
        MyObject obj = new MyObject("Object1");
        threadLocal.set(obj); // 将对象存入ThreadLocal
        System.out.println("threadLocal设置成功");
        obj = null; // 移除外部强引用

        // 此时，虽然obj为null，但ThreadLocal变量仍然持有对MyObject的强引用
        // 因此，MyObject实例及其大字节数据不会被垃圾回收
        System.gc(); // 手动触发垃圾回收

        // 尝试从ThreadLocal中获取对象，应该返回非null
        System.out.println("Object after GC: " + threadLocal.get());

        // 现在，我们移除ThreadLocal变量的强引用
        threadLocal = null;
        //threadLocal.remove();
        System.gc(); // 再次触发垃圾回收

        // 由于ThreadLocal变量现在为null，且没有其他强引用指向MyObject
        // MyObject实例及其大字节数据现在可以被垃圾回收
        // 尝试从ThreadLocal中获取对象，应该返回null（假设ThreadLocalMap已被清理）
        System.out.println("Object after nullifying ThreadLocal: " + threadLocal.get());
    }

    static class MyObject {
        private String name;
        private byte[] largeData;

        public MyObject(String name) {
            this.name = name;
            this.largeData = new byte[50*1024 * 1024];
        }


    }
}