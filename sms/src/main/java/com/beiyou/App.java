package com.beiyou;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.Method;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.HashMap;
import java.util.Map;

@SpringBootApplication

public class App {

    // 消息中心
    public static void main(String[] args) {


        SpringApplication.run(App.class, args);


        String url = "https://dfsns.market.alicloudapi.com/data/send_sms";

        // Authorization:APPCODE 363efa0e07bb4c368b05f500db153ec5
        // content=code%3A8888&phone_number=17538057307&template_id=TPL_0000

        // [北游教育] 您的验证码是 %{code}，%{mg}
        HttpRequest httpRequest = HttpRequest.of(url)
                .method(Method.POST)
                .header("Authorization", "APPCODE 363efa0e07bb4c368b05f500db153ec5")
                .body("content=code:9999&phone_number=18239168702&template_id=TPL_0000&msg=欢迎");
         HttpResponse execute = httpRequest.execute();
        ThreadUtil.safeSleep(5000 * 9000);

    }

}
