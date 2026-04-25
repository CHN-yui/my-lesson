package com.mdkj.controller;

import com.mdkj.dto.OrderStatusDTO;
import com.mdkj.result.R;
import com.mdkj.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付业务
 */
@RestController
@RequestMapping("/api/Payment")
public class PaymentController {
    @Autowired
    private OrderService orderService;

    /**
     * 获取支付二维码
     */
    @GetMapping("/qrcode")
    public R<?> qrcode(@RequestParam("orderId") Long orderId) {
        return R.ok("获取成功", orderService.getPayQrcode(orderId));
    }

    /**
     * 支付回调
     */
    @PostMapping("/callback")
    public R<?> callback(@RequestBody OrderStatusDTO dto) {
        orderService.handlePayCallback(dto.getOrderId(), dto.getTradeNo(), dto.getPayType(), dto.getCallbackMsg());
        return R.ok("回调处理成功");
    }

    /**
     * 支付宝异步回调（沙盒/正式一致）
     */
    @PostMapping("/aliCallback")
    public String aliCallback(HttpServletRequest request) {
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String[] values = entry.getValue();
            if (values != null && values.length > 0) {
                params.put(entry.getKey(), values[0]);
            }
        }
        orderService.handleAliPayCallback(params);
        return "success";
    }

    /**
     * 查询订单状态
     */
    @GetMapping("/status")
    public R<?> status(@RequestParam("orderId") Long orderId) {
        return R.ok("查询成功", orderService.queryOrderStatus(orderId));
    }
}
