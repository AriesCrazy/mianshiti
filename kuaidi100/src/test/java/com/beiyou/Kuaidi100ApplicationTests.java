package com.beiyou;

import com.beiyou.model.KD100Info;
import com.beiyou.service.TestService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Kuaidi100ApplicationTests {

    @Autowired
   private TestService testService;
    @Test
    void test() throws Exception {


        int a =  testService.add(1,2);

        Assert.assertEquals(3,a);

       // Assert.assertTrue(a==3);

    }

    @Test
    void test2() throws Exception {

          let  a  = null;
          let b;

        KD100Info  kd100Info  = new KD100Info();

        Assert.assertNull("kd100Info 为空",kd100Info);


    }

}
