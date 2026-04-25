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
* 季次表
* @TableName season
*/
@HeadStyle(horizontalAlignment = HorizontalAlignmentEnum.LEFT, verticalAlignment = VerticalAlignmentEnum.CENTER)
@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.LEFT, verticalAlignment = VerticalAlignmentEnum.CENTER)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "season")
public class Season implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
    * 主键
    */
    @ExcelProperty(value = {"季次表","主键"})
    private Long id;
    /**
    * 标题
    */
    @ExcelProperty(value = {"季次表","标题"})
    private String title;
    /**
    * 描述
    */
    @ExcelProperty(value = {"季次表","描述"})
    private String info;
    /**
    * 课程表ID，课程表外键
    */
    @ExcelProperty(value = {"季次表","课程表ID，课程表外键"})
    private Long fkCourseId;
    /**
    * 序号
    */
    @ExcelProperty(value = {"季次表","序号"})
    private Long idx;
    /**
    * 数据版本
    */
    @ExcelProperty(value = {"季次表","数据版本"})
    private Long version;
    /**
    * 0未删除，1已删除
    */
    @ExcelProperty(value = {"季次表","0未删除，1已删除"})
    private Integer deleted;
    /**
    * 创建时间
    */
    @ExcelProperty(value = {"季次表","创建时间"})
    private Date created;
    /**
    * 修改时间
    */
    @ExcelProperty(value = {"季次表","修改时间"})
    private Date updated;
}
