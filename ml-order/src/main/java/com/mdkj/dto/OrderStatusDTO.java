package com.mdkj.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderStatusDTO {
    private Long orderId;
    private Integer status;
    private Integer payType;
    private BigDecimal payAmount;
    private String tradeNo;
    private String callbackMsg;
}
