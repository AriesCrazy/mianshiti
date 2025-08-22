package com.example;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableCaching
public class SpringCatchApplication {

    public static void main(String[] args) {


        ConfigurableApplicationContext ctx = SpringApplication.run(SpringCatchApplication.class, args);


    }

}
