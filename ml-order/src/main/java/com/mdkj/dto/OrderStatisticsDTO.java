package com.mdkj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatisticsDTO {
    private Long totalOrder;
    private Long unpaidOrder;
    private Long paidOrder;
    private Long cancelOrder;
    private BigDecimal totalAmount;
    private BigDecimal paidAmount;
}
