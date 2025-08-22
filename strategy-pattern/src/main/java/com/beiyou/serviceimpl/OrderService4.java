package com.beiyou.serviceimpl;

import com.beiyou.dto.OrderingDto;
import com.beiyou.service.Ordering;
import org.springframework.stereotype.Service;

@Service("yushou")
public class OrderService4 implements Ordering {
    @Override
    public String process(OrderingDto dto) {
        return "预售订单";
    }
}
