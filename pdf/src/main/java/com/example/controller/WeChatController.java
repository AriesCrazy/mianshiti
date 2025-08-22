package com.example.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weixin")
public class WeChatController {

    private final  String appid ="wx84e44fcb3a08e24c";
    private final  String secret ="83263c2dc46a62f8e8580afb994a5ee2";
    @GetMapping("/access_token")
    public JSONObject getOpenId(@RequestParam String code){

        String url="https://api.weixin.qq.com/sns/jscode2session?appid="
                +appid+"&secret="+secret+"&js_code="+code+"&grant_type=authorization_code";

        String s = HttpUtil.get(url);
        JSONObject entries = JSONUtil.parseObj(s);
        return entries;
    }
}
