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
* 购物车表
* @TableName cart
*/
@HeadStyle(horizontalAlignment = HorizontalAlignmentEnum.LEFT, verticalAlignment = VerticalAlignmentEnum.CENTER)
@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.LEFT, verticalAlignment = VerticalAlignmentEnum.CENTER)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "cart")
public class Cart implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
    * 主键
    */
    @ExcelProperty(value = {"购物车表","主键"})
    private Long id;
    /**
    * 用户ID，用户表外键
    */
    @ExcelProperty(value = {"购物车表","用户ID，用户表外键"})
    private Long fkUserId;
    /**
    * 用户账号（冗余）
    */
    @ExcelProperty(value = {"购物车表","用户账号（冗余）"})
    private String username;
    /**
    * 课程ID，课程表外键
    */
    @ExcelProperty(value = {"购物车表","课程ID，课程表外键"})
    private Long fkCourseId;
    /**
    * 课程标题（冗余）
    */
    @ExcelProperty(value = {"购物车表","课程标题（冗余）"})
    private String courseTitle;
    /**
    * 课程封面图（冗余）
    */
    @ExcelProperty(value = {"购物车表","课程封面图（冗余）"})
    private String courseCover;
    /**
    * 课程单价，单位元（冗余）
    */
    @ExcelProperty(value = {"购物车表","课程单价，单位元（冗余）"})
    private BigDecimal coursePrice;
    /**
    * 数据版本
    */
    @ExcelProperty(value = {"购物车表","数据版本"})
    private Long version;
    /**
    * 0未删除，1已删除
    */
    @ExcelProperty(value = {"购物车表","0未删除，1已删除"})
    private Integer deleted;
    /**
    * 创建时间
    */
    @ExcelProperty(value = {"购物车表","创建时间"})
    private Date created;
    /**
    * 修改时间
    */
    @ExcelProperty(value = {"购物车表","修改时间"})
    private Date updated;
}
