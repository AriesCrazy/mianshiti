package com.beiyou.controller;

import cn.hutool.json.JSONUtil;
import com.beiyou.model.KD100Info;
import com.google.gson.Gson;
import com.kuaidi100.sdk.api.QueryTrack;
import com.kuaidi100.sdk.contant.CompanyConstant;
import com.kuaidi100.sdk.core.IBaseClient;
import com.kuaidi100.sdk.pojo.HttpResult;
import com.kuaidi100.sdk.request.QueryTrackParam;
import com.kuaidi100.sdk.request.QueryTrackReq;
import com.kuaidi100.sdk.utils.SignUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kuaidi")
public class KD100Controller {

    @Value("${kuaidi100.customer}")
    private String customer;
    @Value("${kuaidi100.key}")
    private String key;
    @GetMapping
    public KD100Info getKD100Info(String com, String number) throws Exception {

        //如何避免多次查询
        //? ？
        // com + number = redis key， ttl 1小时

        QueryTrackReq queryTrackReq = new QueryTrackReq();
        QueryTrackParam queryTrackParam = new QueryTrackParam();
//        queryTrackParam.setCom(CompanyConstant.YT);
        queryTrackParam.setCom(com);
        queryTrackParam.setNum(number);
        String param = new Gson().toJson(queryTrackParam);
        queryTrackReq.setParam(param);
        queryTrackReq.setCustomer(customer);
        queryTrackReq.setSign(SignUtils.querySign(param ,key,customer));
        IBaseClient baseClient = new QueryTrack();
        HttpResult httpResult = baseClient.execute(queryTrackReq);
        String body = httpResult.getBody();
        KD100Info kd100Info = JSONUtil.toBean(body, KD100Info.class);

        return kd100Info;
    }

    //@GetMapping
    public void callback(String com, String number) throws Exception {
        // 删除redis 里面的key  ： com+number


    }
}
