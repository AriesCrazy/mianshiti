package com.beiyou.mongodb;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.beiyou.mongodb.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class InsertTests {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 插入文档
     */
    @Test
    void insert() {
        Person person =new Person();
        person.setId(13L);
        person.setName("张三");
        person.setAge(26);
        person.setAddress("河南郑州");
        person.setCreateTime(LocalDateTimeUtil.now());
        mongoTemplate.insert(person);

    }
    /**
     * 自定义集合，插入文档
     */
    @Test
    public void insertCustomCollection() throws Exception {
        Person person =new Person();
        person.setId(1L);
        person.setName("张三");
        person.setAge(26);
        person.setCreateTime(LocalDateTimeUtil.now());
        mongoTemplate.insert(person, "custom_person");
    }
    /**
     * 批量插入文档
     */
    @Test
    public void insertBatch() throws Exception {
        List<Person> personList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Person person =new Person();
            person.setId((long) i);
            person.setName("张三"+i);
            person.setAge(10+i);
            person.setCreateTime(LocalDateTimeUtil.now());
            personList.add(person);

        }

        mongoTemplate.insertAll(personList);
    }
    /**
     * 存储文档，如果没有插入，否则更新
     * 在存储文档的时候会通过主键 ID 进行判断，如果存在就更新，否则就插入
     */
    @Test
    public void save() throws Exception {
        Person person =new Person();
        person.setId(1L);
        person.setName("张三9");
        person.setAge(26);
        person.setCreateTime(LocalDateTimeUtil.now());
        mongoTemplate.save(person);
    }

}
