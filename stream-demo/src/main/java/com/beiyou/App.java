package com.beiyou;

import com.beiyou.model.User;
import org.springframework.util.NumberUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class App {
    public static void main(String[] args) {

        List<User> users = new ArrayList<>();
        users.add(new User("京爷1", 67, "男", "北京", 1200));
        users.add(new User("京爷2", 56, "男", "北京", 2100));
        users.add(new User("海哥1", 20, "男", "上海", 3200));
        users.add(new User("海哥2", 30, "男", "上海", 2600));
        users.add(new User("苏姐1", 21, "女", "苏州", 2800));
        users.add(new User("苏姐2", 25, "女", "苏州", 1500));

        //1.根据城市分组
//        Map<String, List<User>> map = users.stream().collect(Collectors.groupingBy(User::getCity));
//        map.forEach((k, v) -> {
//            System.out.println( "城市:" + k);
//            System.out.println(v);
//        });
        //2.列出每个城市的销售冠军
        Map<String, Optional<User>> map2 = users.stream().collect(
                Collectors.groupingBy(User::getCity, Collectors.maxBy(Comparator.comparing(User::getMoney))));

//        map2.forEach((k, v) -> {
//            System.out.println( "城市:" + k);
//            System.out.println(v.get());
//        });
        //3. 求每个城市的销售总金额
       // Map<String, Integer> map3 = users.stream().collect(Collectors.groupingBy(User::getCity, Collectors.summingInt(User::getMoney)));

        //4.全国销售总金额
        int sum = users.stream().mapToInt(User::getMoney).sum();
       // System.out.println(sum);
       //5.过滤出年龄大于22岁的员工
        List<User> collect = users.stream().filter(user -> user.getAge() > 22 && user.getSex().equals("女")).collect(Collectors.toList());
        //System.out.println(collect);
        //6.最好与最差的员工的销售金额
        OptionalInt max = users.stream().mapToInt(User::getMoney).max();
        System.out.println(max.getAsInt());
        OptionalInt min = users.stream().mapToInt(User::getMoney).min();
        //System.out.println(min.getAsInt());
        //7.人均销售金额
        OptionalDouble average = users.stream().mapToInt(User::getMoney).average();
        //System.out.println(average.getAsDouble());
        //8.全国根据销售金额排序
        users.sort(Comparator.comparingInt(User::getMoney).reversed());

        List<User> collect1 = users.stream()
                .sorted(Comparator.comparingInt(User::getMoney).reversed())
                .collect(Collectors.toList());
        //9.全国有多少销售城市
        List<String> collect2 = users.stream().map(User::getCity).distinct().collect(Collectors.toList());
    }


}
