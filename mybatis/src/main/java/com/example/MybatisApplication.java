package com.example;

import com.example.dao.StudentDao;
import com.example.model.Student;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan
public class MybatisApplication {

    public static void main(String[] args) {


        ConfigurableApplicationContext ctx = SpringApplication.run(MybatisApplication.class, args);
        SqlSessionFactory sqlSessionFactory = ctx.getBean(SqlSessionFactory.class);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentDao mapper = sqlSession.getMapper(StudentDao.class);
        //查询1
        Student student = mapper.select(1);
        System.out.println(student);
        sqlSession.commit();
        //更新
       // mapper.update(Student.builder().name("你好").id(2).build());
        //查询2
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        StudentDao mapper2 = sqlSession2.getMapper(StudentDao.class);
        Student student2 = mapper2.select(1);
        System.out.println(student2);
    }

}
