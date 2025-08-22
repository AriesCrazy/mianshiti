package com.beiyou.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// Comparator  Comparable 的区别
// 相同点：都是比较器
// 不同点：1. Comparable 类必须去实现，属于内部比较器，  Comparator 解耦，跟类无关 外部比较器
//       2.  Comparable单一性，类定义排序规则之后，就不能根据其它方式排序
//           Comparator多样性：灵活的方式来指定对象的排序规则，而不必修改类本身

//Comparable 缺点： 1. 类必须实现Comparable 2.排序规则改变，类必须更改（排序规则与类耦合在一起）
public class Boy  implements  Comparable<Boy>{
    private Integer id;
    private Integer age;
    private String name;

    @Override
    //second
    public int compareTo(Boy other) {
        // 正数   大的排在前面
        // 负数   大的排前面
        int i   =   this.getAge() - other.getAge() ;
        if(i == 0){
            return other.getId() - this.getId();
        }
        return  i;
    }
}





