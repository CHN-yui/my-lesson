package com.mdkj.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.annotation.write.style.HeadStyle;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;
import com.alibaba.excel.enums.poi.VerticalAlignmentEnum;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
* 订单表
* @TableName order
*/
@HeadStyle(horizontalAlignment = HorizontalAlignmentEnum.LEFT, verticalAlignment = VerticalAlignmentEnum.CENTER)
@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.LEFT, verticalAlignment = VerticalAlignmentEnum.CENTER)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "order")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
    * 主键
    */
    @ExcelProperty(value = {"订单表","主键"})
    private Long id;
    /**
    * 编号
    */
    @ExcelProperty(value = {"订单表","编号"})
    private String sn;
    /**
    * 总金额
    */
    @ExcelProperty(value = {"订单表","总金额"})
    private BigDecimal totalAmount;
    /**
    * 实际支付总金额
    */
    @ExcelProperty(value = {"订单表","实际支付总金额"})
    private BigDecimal payAmount;
    /**
    * 支付方式，0未支付，1微信，2支付宝，3其他
    */
    @ExcelProperty(value = {"订单表","支付方式，0未支付，1微信，2支付宝，3其他"})
    private Integer payType;
    /**
    * 订单状态，0未付款，1已付款，2已取消，3其他
    */
    @ExcelProperty(value = {"订单表","订单状态，0未付款，1已付款，2已取消，3其他"})
    private Integer status;
    /**
    * 用户ID，用户表外键
    */
    @ExcelProperty(value = {"订单表","用户ID，用户表外键"})
    private Long fkUserId;
    /**
    * 用户账号（冗余）
    */
    @ExcelProperty(value = {"订单表","用户账号（冗余）"})
    private String username;
    /**
    * 优惠卷ID，优惠卷表外键
    */
    @ExcelProperty(value = {"订单表","优惠卷ID，优惠卷表外键"})
    private Long fkCouponsId;
    /**
    * 描述
    */
    @ExcelProperty(value = {"订单表","描述"})
    private String info;
    /**
    * 数据版本
    */
    @ExcelProperty(value = {"订单表","数据版本"})
    private Long version;
    /**
    * 0未删除，1已删除
    */
    @ExcelProperty(value = {"订单表","0未删除，1已删除"})
    private Integer deleted;
    /**
    * 创建时间
    */
    @ExcelProperty(value = {"订单表","创建时间"})
    private Date created;
    /**
    * 修改时间
    */
    @ExcelProperty(value = {"订单表","修改时间"})
    private Date updated;
}
