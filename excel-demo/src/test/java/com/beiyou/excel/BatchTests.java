package com.beiyou.excel;

import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.PageUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.sax.handler.BeanRowHandler;
import cn.hutool.poi.excel.sax.handler.RowHandler;
import com.beiyou.entity.Student;
import com.beiyou.entity.StudentBeanRowHandler;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;


import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootTest
class BatchTests {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Resource
    private ThreadPoolExecutor htStudentImportThreadPool;


    @Test
    void test1() throws IOException {


       // ExcelReader reader = ExcelUtil.getReader(ResourceUtil.getStream("100.xlsx"));
       // int rowCount = reader.getRowCount();
        int pageSize = 5000;
        int pageCount = PageUtil.totalPage(1000000, pageSize);

        StopWatch sw = new StopWatch();
        sw.start();
        for (int i = 1; i <= pageCount; i++) {

            int finalI1 = i;
            htStudentImportThreadPool.execute(()->{

                ExcelUtil.readBySax("100.xlsx", 0, new StudentBeanRowHandler(0,(finalI1 -1)*pageSize,(finalI1)*pageSize,Student.class));
            });

        }
        htStudentImportThreadPool.shutdown();
        sw.stop();
        System.out.println(sw.getTotalTimeSeconds());
      System.in.read();
    }



}
