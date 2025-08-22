package com.beiyou.dao;

import com.beiyou.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentDao {
    Integer insertBatch(@Param("students") List<Student> students);
}
