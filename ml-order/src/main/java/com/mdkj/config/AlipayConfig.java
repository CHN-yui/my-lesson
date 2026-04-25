package com.mdkj.config;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 支付宝配置占位
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "alipay")
public class AlipayConfig {
    private String appId;
    private String privateKey;
    private String alipayPublicKey;
    private String notifyUrl;

    @PostConstruct
    public void init() {
        Config options = new Config();
        options.protocol = "https";
        options.gatewayHost = "openapi-sandbox.dl.alipaydev.com";
        options.signType = "RSA2";
        options.appId = appId;
        options.merchantPrivateKey = privateKey;
        options.alipayPublicKey = alipayPublicKey;
        options.notifyUrl = notifyUrl;
        Factory.setOptions(options);
    }
}
