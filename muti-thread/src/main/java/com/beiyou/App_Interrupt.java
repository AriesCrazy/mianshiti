package com.beiyou;


import cn.hutool.core.thread.ThreadUtil;

import java.io.IOException;

/**
 * 线程中断
 * 在Java中，interrupt() 是一个用于中断线程的方法。当调用线程的 interrupt() 方法时，它会设置线程的中断状态为 "中断"。
 * 但是，interrupt() 方法并不能直接中断线程的执行。它只是给线程发送一个中断信号，线程可以根据自己的逻辑来处理这个信号。具体来说，当线程被中断时，它可以选择在合适的时机退出执行。
 * 另外，一些阻塞方法（例如 sleep()、wait() 等）在收到中断信号时，会立即抛出 InterruptedException 异常，以提前结束阻塞状态。
 * 总之，interrupt() 方法用于向线程发送中断信号，而线程可以根据自身的逻辑来处理这个信号，从而实现线程的中断操作
 */
public class App_Interrupt {

    public static void main(String[] args) throws IOException {
        Thread t1 = new Thread(() -> {
               System.out.println("开始干活了");
               while (!Thread.currentThread().isInterrupted()){
                   System.out.println("doing....");
               }

            System.out.println("不干了");
        });
        t1.start();
        ThreadUtil.safeSleep(10*1000);
        t1.interrupt(); //主线程给操作系统发一个信号，说请帮忙让小弟t1停下来，不要再干了
        // B 调用  A.interrupt()  B向操作系统发送一个信号，让A停下来
        System.in.read();

    }
}
