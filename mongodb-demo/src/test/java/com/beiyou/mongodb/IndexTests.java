package com.beiyou.mongodb;

import com.beiyou.mongodb.model.Person;

import com.mongodb.client.ListIndexesIterable;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@SpringBootTest
class IndexTests {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 创建升序索引
     */
    @Test
    public void createAscendingIndex() {
        // 设置字段名称
        String field = "age";
        // 创建索引
        mongoTemplate.getCollection("my_person").createIndex(Indexes.ascending(field));
    }

    /**
     * 根据索引名称移除索引
     */
    @Test
    public void removeIndex() {
        // 设置字段名称
        String field = "age_1";
        // 删除索引
        mongoTemplate.getCollection("my_person").dropIndex(field);
    }


    /**
     * 查询集合中所有的索引
     */
    @Test
    public void getIndexAll() {
        // 获取集合中所有列表
        ListIndexesIterable<Document> indexList =   mongoTemplate.getCollection("my_person").listIndexes();
        // 获取集合中全部索引信息
        for (Document document : indexList) {
            System.out.println("索引列表：" + document);
        }
    }
}
//我们还可以通过在实体类上加注解方式来创建索引

