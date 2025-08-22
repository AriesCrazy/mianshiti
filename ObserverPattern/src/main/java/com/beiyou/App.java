package com.beiyou;


import com.beiyou.listener.MyApplicationStartingEventListener;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class App  {

    public static void main(String[] args)  {
        SpringApplication.run(App.class, args);

    }

}
