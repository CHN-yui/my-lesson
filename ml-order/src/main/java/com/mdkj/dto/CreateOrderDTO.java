package com.mdkj.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderDTO {
    private Long userId;
    private String username;
    private List<Long> cartIds;
    private Integer payType;
    private Long couponsId;
    private String info;
}
