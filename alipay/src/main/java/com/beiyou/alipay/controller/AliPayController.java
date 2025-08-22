package com.beiyou.alipay.controller;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AliPayController {

    @GetMapping("/api/alipay")
    public String pay(String subject, String outTradeNo, String totalAmount, String returnUrl) throws Exception {

        AlipayTradePagePayResponse pay = Factory.Payment.Page().pay(subject, outTradeNo, totalAmount, returnUrl);
        String body = pay.getBody();
        return body;
    }

    //支付宝支付回调
    @PostMapping("/api/alipay/notify")
    public String aliPayNotify(@RequestParam Map params) throws Exception {

        String  mm ="";
        return "fail";
//        String trade_status = params.getOrDefault("trade_status", "").toString();
//        if (trade_status.equals("TRADE_SUCCESS")) {
//
//            //验证是否是来只支付宝的回调
//            Boolean b = Factory.Payment.Common().verifyNotify(params);
//            if (b) {
//                //业务逻辑。把订单状态改成支付成功
//                String out_trade_no = params.getOrDefault("out_trade_no", "").toString();
//                //更改数据库该订单的状态
//                return "success";
//            }
//
//        }
//        return "fail";
    }
}
