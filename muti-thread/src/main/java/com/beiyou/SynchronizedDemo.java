package com.beiyou;

public class SynchronizedDemo {

    public synchronized void test1(){   // synchronized在方法上锁的是 this ：当前对象

    }
    //与上面作用一样
    public  void testX(){
        synchronized (this){

        }
    }

    public  static  synchronized void test2(){ //  SynchronizedDemo.class 当前类对象

    }

    private Object locker  = new Object();

    public void  test3(){
        //////
        /////
        ///
        synchronized (locker){

          ///
         ///
        }
    }
}

