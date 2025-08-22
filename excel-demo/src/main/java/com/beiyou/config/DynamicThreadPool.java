package com.beiyou.config;


import cn.hutool.core.thread.BlockPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 配置线程池，以提高导入效率
 */
@Configuration
public class DynamicThreadPool {

    @Bean(name = "htStudentImportThreadPool")
    public ThreadPoolExecutor htStudentImportThreadPool() {
        int processors =3; Runtime.getRuntime().availableProcessors();//获取系统处理器数量
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                processors + 1,//最小线程数：系统处理器数量 + 1
                processors * 2 + 1,//最大线程数：系统处理器数量 * 2 + 1
                10 * 60,//线程空闲时间：10分钟
                TimeUnit.SECONDS,//单位：秒
                new LinkedBlockingQueue<>(10),//队列长度：1000000
                new BlockPolicy());

        //设置线程的名称前缀


        return threadPoolExecutor;
    }

}