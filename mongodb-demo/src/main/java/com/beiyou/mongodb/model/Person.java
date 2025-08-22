package com.beiyou.mongodb.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "my_person")
@Data
public class Person {


    static  int a = 0;
    static  Person b = new Person();

    @Id // _id
    private Long id;
    private String name;
    private Integer age;
    private String address;
    /**
     * 创建一个10秒之后文档自动删除, 类似 redis ttl
     */
    @Indexed(expireAfterSeconds=10)
    private LocalDateTime createTime;
}