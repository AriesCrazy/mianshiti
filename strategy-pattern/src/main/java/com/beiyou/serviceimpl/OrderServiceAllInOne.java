package com.beiyou.serviceimpl;

import ch.qos.logback.classic.pattern.EnsureExceptionHandling;
import com.beiyou.dto.OrderingDto;
import com.beiyou.service.Ordering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;

@Service
public class OrderServiceAllInOne  {

    //持有一个对策略对象的引用，在其内部通过该策略接口调用具体策略类中的算法或行为。
    @Autowired
    private Map<String,Ordering>  orderingMap;

    public String process(OrderingDto dto) {
        String orderType = dto.getOrderType();
        Ordering ordering = orderingMap.get(orderType);
        return  ordering.process(dto);
    }
}
