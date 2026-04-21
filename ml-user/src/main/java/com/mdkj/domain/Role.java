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
* 角色表
* @TableName role
*/
@HeadStyle(horizontalAlignment = HorizontalAlignmentEnum.LEFT, verticalAlignment = VerticalAlignmentEnum.CENTER)
@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.LEFT, verticalAlignment = VerticalAlignmentEnum.CENTER)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "role")
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
    * 主键
    */
    @ExcelProperty(value = {"角色表","主键"})
    private Long id;
    /**
    * 标题
    */
    @ExcelProperty(value = {"角色表","标题"})
    private String title;
    /**
    * 序号
    */
    @ExcelProperty(value = {"角色表","序号"})
    private Long idx;
    /**
    * 描述
    */
    @ExcelProperty(value = {"角色表","描述"})
    private String info;
    /**
    * 数据版本
    */
    @ExcelProperty(value = {"角色表","数据版本"})
    private Long version;
    /**
    * 0未删除，1已删除
    */
    @ExcelProperty(value = {"角色表","0未删除，1已删除"})
    private Integer deleted;
    /**
    * 创建时间
    */
    @ExcelProperty(value = {"角色表","创建时间"})
    private Date created;
    /**
    * 修改时间
    */
    @ExcelProperty(value = {"角色表","修改时间"})
    private Date updated;
}
