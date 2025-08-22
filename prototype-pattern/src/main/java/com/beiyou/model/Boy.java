package com.beiyou.model;

import cn.hutool.json.JSONUtil;
import lombok.*;

import javax.annotation.PostConstruct;
import java.io.*;

@Setter
@Getter
public class Boy  implements  Cloneable, Serializable     {

    public Boy() {

    }
    public Boy( String name, Integer age ) {
        this.name = name;
        this.age = age;
    }

    private String name; // 引用类型
    private int age;  //8大基本类型
    private Address address; //引用类型

    @SneakyThrows
    @Override
    public Object clone() throws CloneNotSupportedException {
        //return super.clone(); // 调用 Object 的 clone()  浅copy
        //如何实现深copy
        // 1. 所有引用类型的字段也实现Cloneable接口，调用 clone ()
        // 2.序列化-Stream序列化、反序列化

     //   ByteArrayOutputStream bos = new ByteArrayOutputStream();
     //   ObjectOutputStream oos = new ObjectOutputStream(bos);
     //   oos.writeObject(this);


     //   ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
     //   ObjectInputStream ois = new ObjectInputStream(bis);
       // return (Boy) ois.readObject();
        //3. 使用json序列化-json序列化、反序列化(反射)

        String json = JSONUtil.toJsonStr(this);
        Boy boy3 = JSONUtil.toBean(json, Boy.class);

       return boy3;
    }
}
