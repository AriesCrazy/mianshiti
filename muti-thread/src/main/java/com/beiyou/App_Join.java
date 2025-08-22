package com.beiyou;


import cn.hutool.core.thread.ThreadUtil;
import com.beiyou.model.Counter;

import java.io.IOException;

/**
 * Join
 * //https://blog.csdn.net/wzwjm123/article/details/124829250
 */
public class App_Join {

    public static void main(String[] args) throws InterruptedException, IOException {

        System.out.println("main线程开始");

        Thread t1 = new Thread(() -> {
            System.out.println("t1 开始" + Thread.currentThread().getName());
            ThreadUtil.safeSleep(30 * 1000);
            System.out.println("t1 结束" + Thread.currentThread().getName());
        });
        t1.start();
        t1.join(12); //join 会等待，主线程会等待子线程执行完成之后，才继续执行
        // B 调用  A.join()  B等待 A执行完成之后，才会继续执行
        System.out.println("main 线程结束了");
        while (true){

        }


    }

}
