package com.beiyou.serviceimpl;

import com.beiyou.dto.OrderingDto;
import com.beiyou.service.Ordering;
import org.springframework.stereotype.Service;

@Service("tuangou")
public class OrderService2 implements Ordering {
    @Override
    public String process(OrderingDto dto) {
        return "团购订单";
    }
}
