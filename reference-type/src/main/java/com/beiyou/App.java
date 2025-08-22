package com.beiyou;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.thread.ThreadUtil;
import com.beiyou.model.Student;

import java.io.IOException;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


//@SpringBootApplication
public class App {

    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {

        //引用：  强    弱   软  虚
        //强
//          Student student = new Student();
//          student = null;
//          System.gc();
//          System.out.println(student);
//          System.in.read();

        //弱

//        Student s2 = new Student();
//        WeakReference<Student> weakReference = new WeakReference<>(s2);
//        System.out.println(weakReference.get());
//        s2 = null;
//        System.gc();
//        System.out.println(weakReference.get());

        //软引用
//        Student s3 = new Student();
//        SoftReference<Student> softReference = new SoftReference<>(s3);
//        System.out.println(softReference.get());
//        s3.setBody(new byte[10 * 1024 * 1024]); // 添加10MB数据
//        s3 = null;
//        System.gc();
//        System.out.println(softReference.get());
//        List<byte[]> list = new ArrayList<>();
//        int i = 1;
//        while (true) {
//             list.add(new byte[5*1024*1024]);
//            System.gc();
//            System.out.println("--------------"+ DateUtil.format(LocalDateTimeUtil.now(),"HH:mm:ss") +"------------------");
//            System.out.println("第"+i+"次加了10M");
//            System.out.println(softReference.get());
//            ThreadUtil.sleep(1000);
//
//            i++;
//        }

        //虚引用
        Student s4 = new Student();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Student> phantomReference = new PhantomReference<>(s4,referenceQueue);
        System.out.println(phantomReference.get());
        s4 = null;
        System.gc();
        System.out.println(phantomReference.get());

    }

}
