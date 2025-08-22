package com.beiyou.serviceimpl;

import com.beiyou.dto.OrderingDto;
import com.beiyou.service.Ordering;
import org.springframework.stereotype.Service;

@Service("putong")
public class OrderService1 implements Ordering {
    @Override
    public String process(OrderingDto dto) {
        return "普通订单";
    }
}
