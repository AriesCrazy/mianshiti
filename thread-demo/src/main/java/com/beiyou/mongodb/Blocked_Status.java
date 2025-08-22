package com.beiyou.mongodb;

import cn.hutool.core.thread.ThreadUtil;

public class Blocked_Status {
    private static  final  Object locker = new Object();
    public static void main(String[] args) {
        Thread t1 = new Thread(new BlockedThread());
        Thread t2 = new Thread(new BlockedThread());
        t1.start();
        t2.start();
        //让主线程停留1秒
        ThreadUtil.safeSleep(1000);
        //等待1s后打印线程状态
        System.out.println("t1 -> "+ t1.getState()); // TIMED_WAITING 当 t1 在sleep的时候，有没有释放锁？ t1 是抱着锁睡觉的
        System.out.println("t2 -> "+ t2.getState()); //  BLOCKED
    }

    private static  class BlockedThread  implements Runnable{
        @Override
        public void run() {
             synchronized (locker){
                 ThreadUtil.safeSleep(5000);// 模拟业务，抱着锁睡眠5秒
             }
        }
    }
}
