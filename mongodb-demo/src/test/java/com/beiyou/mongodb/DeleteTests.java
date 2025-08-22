package com.beiyou.mongodb;

import com.beiyou.mongodb.model.Person;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

@SpringBootTest
class DeleteTests {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 删除符合条件的所有文档
     */
    @Test
    public void remove() throws Exception {
      //删除年龄小于18的所有人
        Query query = new Query(Criteria.where("age").lt(18));
        DeleteResult result = mongoTemplate.remove(query, Person.class);
        System.out.println("删除条数：" + result.getDeletedCount());
    }

    /**
     * 删除符合条件的单个文档，并返回删除的文档
     */
    @Test
    public void findAndRemove() throws Exception {

        Query query = new Query(Criteria.where("id").is(4));
        Person result = mongoTemplate.findAndRemove(query, Person.class);
        System.out.println("删除的文档数据：" + result);
    }

    /**
     * 删除符合条件的所有文档，并返回删除的文档
     */
    @Test
    public void findAllAndRemove() throws Exception {

        // 使用 in 删除 符合条件的多条文档，并返回
        Query query = new Query(Criteria.where("id").in(1,2,3));
        List<Person> result = mongoTemplate.findAllAndRemove(query, Person.class);
        System.out.println("删除的文档数据：" + result.toString());
    }

}
