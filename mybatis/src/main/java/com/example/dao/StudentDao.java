package com.example.dao;

import com.example.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface StudentDao {


    Student select(Integer id);

    int update(Student student);
}
