package com.beiyou.mongodb;

public class New_Status {

    public static void main(String[] args) {

        Thread t1 = new Thread(()->{
            System.out.println("线程新建状态");
        });
      //  t1.start();
        System.out.println("t1:"+t1.getState());
    }


}
