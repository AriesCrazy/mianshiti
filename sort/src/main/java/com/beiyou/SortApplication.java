package com.beiyou;

import cn.hutool.core.collection.CollUtil;

import com.beiyou.model.Boy;
import com.beiyou.model.Girl;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@SpringBootApplication

public class SortApplication {


    public static void main(String[] args) {

        Boy boy1 = Boy.builder().id(1).age(23).name("张1-23").build();
        Boy boy2 = Boy.builder().id(2).age(48).name("张2-48").build();
        Boy boy3 = Boy.builder().id(3).age(65).name("张3-65").build();
        Boy boy4 = Boy.builder().id(4).age(48).name("张4-48").build();
        ArrayList<Boy> boyList = CollUtil.newArrayList(boy1,boy2, boy3, boy4);
        Collections.sort(boyList);
//        23, 48, 48, 65

        Girl girl1 = Girl.builder().id(1).age(23).name("张1-23").build();
        Girl girl2 = Girl.builder().id(2).age(48).name("张2-48").build();
        Girl girl3 = Girl.builder().id(3).age(65).name("张3-65").build();
        Girl girl4 = Girl.builder().id(4).age(48).name("张4-48").build();
        ArrayList<Girl> girlList = CollUtil.newArrayList(girl1,girl2, girl3, girl4);
        Collections.sort(girlList, (o1, o2) -> o1.getAge() - o2.getAge());
        Collections.sort(girlList, (o1, o2) -> o2.getId() - o1.getId());
       String  mm = "";

    }

}
