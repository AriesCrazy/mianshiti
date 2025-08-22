package com.beiyou;


import cn.hutool.core.thread.ThreadUtil;

public class Girl {

    private static final Object locker = new Object();

    //饿汉式， 提前实例化一个对象, 即使你不用它，它也会占用空间
    //优点：实现简单
    //缺点：浪费内存
    public final static Girl girl1 = new Girl();

    //volatile :
    // 1. 保证多线程可见性，不保证原子性
    // 2. 禁止指令重排

    private static volatile  Girl singletonGirl;
    // 1.禁止cpu指令重排
    // 2.多线程可见性，但不保证原子性
    //懒汉式
    //double check lock  DCL
    // 第一重 ： 后续慢的线程不需要再与锁打交道了
    // 第二重： 第一次并发进来的时候，如果是多个线程，避免进锁房间的线程重复创建实例对象
    // synchronized  静态方法锁 new object  or  类对象

    // 类对象
    public static synchronized Girl getInstance3(){
       // synchronized (Girl.class){}
        return null;
    }
    //类实例
    public  synchronized Girl getInstance4(){
        // synchronized (this){}
        return null;
    }

    public static Girl getInstance()
    {
        if(singletonGirl == null){ // 1. 一重检测
            synchronized (Girl.class){
                if(singletonGirl==null){
                    singletonGirl = new Girl();
                }

            }

        }

        return  singletonGirl;
    }
    // synchronized  非静态方法锁 this 类的实例
    public  Girl getInstance2()
    {
        if(singletonGirl == null){ // 1. 一重检测
            synchronized (this){
                if(singletonGirl==null){
                    singletonGirl = new Girl();
                }

            }

        }

        return  singletonGirl;
    }
}
