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
import java.util.Date;

/**
* 秒杀表
* @TableName seckill
*/
@HeadStyle(horizontalAlignment = HorizontalAlignmentEnum.LEFT, verticalAlignment = VerticalAlignmentEnum.CENTER)
@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.LEFT, verticalAlignment = VerticalAlignmentEnum.CENTER)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "seckill")
public class Seckill implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
    * 主键
    */
    @ExcelProperty(value = {"秒杀表","主键"})
    private Long id;
    /**
    * 标题
    */
    @ExcelProperty(value = {"秒杀表","标题"})
    private String title;
    /**
    * 描述
    */
    @ExcelProperty(value = {"秒杀表","描述"})
    private String info;
    /**
    * 活动开始时间
    */
    @ExcelProperty(value = {"秒杀表","活动开始时间"})
    private Date startTime;
    /**
    * 活动结束时间
    */
    @ExcelProperty(value = {"秒杀表","活动结束时间"})
    private Date endTime;
    /**
    * 0未开始，1已开始，2已结束
    */
    @ExcelProperty(value = {"秒杀表","0未开始，1已开始，2已结束"})
    private Integer status;
    /**
    * 数据版本
    */
    @ExcelProperty(value = {"秒杀表","数据版本"})
    private Long version;
    /**
    * 0未删除，1已删除
    */
    @ExcelProperty(value = {"秒杀表","0未删除，1已删除"})
    private Integer deleted;
    /**
    * 创建时间
    */
    @ExcelProperty(value = {"秒杀表","创建时间"})
    private Date created;
    /**
    * 修改时间
    */
    @ExcelProperty(value = {"秒杀表","修改时间"})
    private Date updated;
}
