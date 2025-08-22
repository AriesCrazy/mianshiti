package com.beiyou;

import cn.hutool.core.thread.ThreadUtil;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class App {

    public static void main(String[] args) throws IOException {


        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                Girl girl = Girl.getInstance() ;
               System.out.println("->"+girl);
            }).start();
        }


        System.in.read();
    }

}
