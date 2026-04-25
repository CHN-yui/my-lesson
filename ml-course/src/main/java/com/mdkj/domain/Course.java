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
* 课程表
* @TableName course
*/
@HeadStyle(horizontalAlignment = HorizontalAlignmentEnum.LEFT, verticalAlignment = VerticalAlignmentEnum.CENTER)
@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.LEFT, verticalAlignment = VerticalAlignmentEnum.CENTER)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "course")
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
    * 主键
    */
    @ExcelProperty(value = {"课程表","主键"})
    private Long id;
    /**
    * 标题
    */
    @ExcelProperty(value = {"课程表","标题"})
    private String title;
    /**
    * 作者
    */
    @ExcelProperty(value = {"课程表","作者"})
    private String author;
    /**
    * 类别ID，类别表外键
    */
    @ExcelProperty(value = {"课程表","类别ID，类别表外键"})
    private Long fkCategoryId;
    /**
    * 摘要图地址
    */
    @ExcelProperty(value = {"课程表","摘要图地址"})
    private String summary;
    /**
    * 封面图地址
    */
    @ExcelProperty(value = {"课程表","封面图地址"})
    private String cover;
    /**
    * 单价，单位元
    */
    @ExcelProperty(value = {"课程表","单价，单位元"})
    private BigDecimal price;
    /**
    * 序号
    */
    @ExcelProperty(value = {"课程表","序号"})
    private Long idx;
    /**
    * 描述
    */
    @ExcelProperty(value = {"课程表","描述"})
    private String info;
    /**
    * 数据版本
    */
    @ExcelProperty(value = {"课程表","数据版本"})
    private Long version;
    /**
    * 0未删除，1已删除
    */
    @ExcelProperty(value = {"课程表","0未删除，1已删除"})
    private Integer deleted;
    /**
    * 创建时间
    */
    @ExcelProperty(value = {"课程表","创建时间"})
    private Date created;
    /**
    * 修改时间
    */
    @ExcelProperty(value = {"课程表","修改时间"})
    private Date updated;
}
