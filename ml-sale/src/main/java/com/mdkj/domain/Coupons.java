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
* 优惠卷表
* @TableName coupons
*/
@HeadStyle(horizontalAlignment = HorizontalAlignmentEnum.LEFT, verticalAlignment = VerticalAlignmentEnum.CENTER)
@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.LEFT, verticalAlignment = VerticalAlignmentEnum.CENTER)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "coupons")
public class Coupons implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
    * 主键
    */
    @ExcelProperty(value = {"优惠卷表","主键"})
    private Long id;
    /**
    * 兑换码
    */
    @ExcelProperty(value = {"优惠卷表","兑换码"})
    private String code;
    /**
    * 标题
    */
    @ExcelProperty(value = {"优惠卷表","标题"})
    private String title;
    /**
    * 优惠金额，单位分
    */
    @ExcelProperty(value = {"优惠卷表","优惠金额，单位分"})
    private BigDecimal cpPrice;
    /**
    * 描述
    */
    @ExcelProperty(value = {"优惠卷表","描述"})
    private String info;
    /**
    * 优惠卷生效时间
    */
    @ExcelProperty(value = {"优惠卷表","优惠卷生效时间"})
    private Date startTime;
    /**
    * 优惠卷失效时间
    */
    @ExcelProperty(value = {"优惠卷表","优惠卷失效时间"})
    private Date endTime;
    /**
    * 数据版本
    */
    @ExcelProperty(value = {"优惠卷表","数据版本"})
    private Long version;
    /**
    * 0未删除，1已删除
    */
    @ExcelProperty(value = {"优惠卷表","0未删除，1已删除"})
    private Integer deleted;
    /**
    * 创建时间
    */
    @ExcelProperty(value = {"优惠卷表","创建时间"})
    private Date created;
    /**
    * 修改时间
    */
    @ExcelProperty(value = {"优惠卷表","修改时间"})
    private Date updated;
}
