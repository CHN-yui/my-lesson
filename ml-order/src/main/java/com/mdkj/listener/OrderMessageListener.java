package com.mdkj.listener;

import com.mdkj.constant.ML;
import com.mdkj.domain.Order;
import com.mdkj.service.OrderService;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 处理超时未支付订单消息
 */
@Component
@RocketMQMessageListener(
        topic = "order-timeout-topic",
        consumerGroup = "order-timeout-group",
        selectorType = SelectorType.TAG
)
public class OrderMessageListener implements RocketMQListener<String> {
    @Autowired
    private OrderService orderService;

    @Override
    public void onMessage(String message) {
        Long orderId;
        try {
            orderId = Long.valueOf(message);
        } catch (Exception e) {
            return;
        }
        Order order = orderService.getById(orderId);
        if (order == null || order.getDeleted() != null && order.getDeleted() == 1) {
            return;
        }
        if (!ML.Order.UNPAID.equals(order.getStatus())) {
            return;
        }
        Order update = new Order();
        update.setId(orderId);
        update.setStatus(ML.Order.CANCEL);
        update.setInfo("支付超时自动取消");
        update.setUpdated(new Date());
        orderService.updateById(update);
    }
}
