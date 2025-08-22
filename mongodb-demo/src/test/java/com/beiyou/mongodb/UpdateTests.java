package com.beiyou.mongodb;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.beiyou.mongodb.model.Person;
import com.mongodb.client.result.UpdateResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class UpdateTests {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 更新文档，匹配查询到的文档数据中的第一条数据
     * @throws Exception
     */
    @Test
    public void update1() throws Exception {
        //更新对象
//        Person person =new Person();
//        person.setId(1L);
//        person.setName("张三123");
//        person.setAge(32);

        //更新条件    is  类似 = ，  where id = 1
       // Query query= new Query(Criteria.where("id").is(1L));
        Query query= new Query(Criteria.where("age").is(26));
        //更新值
        Update update= new Update().set("name", "张26->18").set("age",18);
        //更新查询满足条件的文档数据（第一条）
        UpdateResult result = mongoTemplate.updateFirst(query,update, Person.class);
        System.out.println("更新条数：" + result.getMatchedCount());
    }
    /**
     * 更新文档，匹配查询到的文档数据中的所有数据
     */
    @Test
    public void updateMany() throws Exception {

        //更新年龄大于等于32的人

        // gt    大于    >
        // gte   大于等于  >=
        // lt    小于     <
        // lte    小于等于     <=
        // in     包含    in ()
        Query query= new Query(Criteria.where("age").gte(18));
        //更新姓名为 “我成人了”
        Update update= new Update().set("name", "张-我成人了");
        //更新查询满足条件的文档数据（全部）
        UpdateResult result = mongoTemplate.updateMulti(query, update, Person.class);
        System.out.println("更新条数：" + result.getMatchedCount());
    }


}
