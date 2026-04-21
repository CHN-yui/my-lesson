package com.mdkj.dto;

import lombok.Data;

import java.io.Serializable;

/** 消息实体类 */
@Data
public class OrderMessage implements Serializable {
    private Long fkUserId;
    private Long fkCourseId;
    private Double price;
    private Double skPrice;
}