package com.mdkj.listener;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * 处理支付成功后的扩展消息
 */
@Component
@RocketMQMessageListener(topic = "order-payment-topic", consumerGroup = "order-payment-group")
public class PaymentCallbackListener implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        // 预留扩展：积分发放、发票、通知等
    }
}
