package com.beiyou.alipay.config;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ConfigurationProperties(prefix = "alipay")
@Data
//1.通过配置文件读取相关参数赋值到这个bean的对应属性上面
//2.
public class AlipayConfig {
    // 应用Id
    private String appId;
    // 应用私有
    private String appPrivateKey;
    // 支付宝公钥
    private String publiceKey;
    // 回调接口路径
    private String notifyUrl;
    // 支付宝网关地址
    private String gatewayHost;

    @PostConstruct
    public void init(){
        Config config = new Config();
        // 基础配置
        config.protocol = "https";
        config.signType = "RSA2";
        config.gatewayHost = this.gatewayHost ;// "openapi-sandbox.dl.alipaydev.com";// 新版沙箱网关地址,上线后这个地址要改成正式

        // 业务配置
        config.appId = this.appId;
        config.merchantPrivateKey = this.appPrivateKey;
        config.alipayPublicKey = this.publiceKey;
        config.notifyUrl = this.notifyUrl;

        // 将配置信息， 添加到相应的工厂类
        Factory.setOptions(config);
        System.out.println("支付宝初始化配置完成");

    }


}
