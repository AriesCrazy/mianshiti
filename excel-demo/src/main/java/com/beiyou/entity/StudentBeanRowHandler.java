package com.beiyou.entity;

import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.poi.excel.sax.handler.BeanRowHandler;
import com.beiyou.dao.StudentDao;

import java.util.ArrayList;
import java.util.List;

public class StudentBeanRowHandler  extends BeanRowHandler<Student> {

    public StudentBeanRowHandler(int headerRowIndex, int startRowIndex, int endRowIndex, Class<Student> clazz) {
        super(headerRowIndex, startRowIndex, endRowIndex, clazz);
    }
     private List<Student> students = new ArrayList<>();
    @Override
    public void doAfterAllAnalysed() {
        StudentDao bean = SpringUtil.getBean(StudentDao.class);
        bean.insertBatch(students);
        System.out.println(Thread.currentThread().getName()+"完成");
    }

    @Override
    public void handleData(int i, long l, Student student) {
        String  mm =  student.getName();
        students.add(student);
    }
}