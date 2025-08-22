package com.example.jvm;

import cn.hutool.core.thread.ThreadUtil;
import com.example.jvm.model.Student;

import java.util.ArrayList;
import java.util.List;


public class VisualVMDemo2 {


    private static List<byte[]> list = new ArrayList<>();    // 创建一个大数组以模拟内存使用

    public static void main(String[] args) throws InterruptedException {


        while (true) {
            // 持续创建对象但不释放引用
            list.add(new byte[100 * 1024]); // 添加1MB数据
            Student student  = new Student().setBody(new byte[1*1024 * 1024]);
            ThreadUtil.safeSleep(1000);
        }
    }
}


