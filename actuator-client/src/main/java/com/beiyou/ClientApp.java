package com.beiyou;

import cn.hutool.core.thread.ThreadUtil;
import com.beiyou.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
//@RestController
@Slf4j
public class ClientApp {

    public static void main(String[] args)
    {
        SpringApplication.run(ClientApp.class, args);
        List<Student> list = new ArrayList<>();
        while (true){
            Student  student  = new Student();
            student.setBody(new byte[1*1024*1024]);
            list.add(student);
            log.info("增加了1M");
            ThreadUtil.safeSleep(1000);
        }



    }


}
