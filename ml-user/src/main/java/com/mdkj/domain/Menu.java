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
* 菜单表
* @TableName menu
*/
@HeadStyle(horizontalAlignment = HorizontalAlignmentEnum.LEFT, verticalAlignment = VerticalAlignmentEnum.CENTER)
@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.LEFT, verticalAlignment = VerticalAlignmentEnum.CENTER)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "menu")
public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
    * 主键
    */
    @ExcelProperty(value = {"菜单表","主键"})
    private Long id;
    /**
    * 标题
    */
    @ExcelProperty(value = {"菜单表","标题"})
    private String title;
    /**
    * 跳转地址
    */
    @ExcelProperty(value = {"菜单表","跳转地址"})
    private String url;
    /**
    * 图标名称
    */
    @ExcelProperty(value = {"菜单表","图标名称"})
    private String icon;
    /**
    * 父菜单主键，0视为根节点
    */
    @ExcelProperty(value = {"菜单表","父菜单主键，0视为根节点"})
    private Long pid;
    /**
    * 序号
    */
    @ExcelProperty(value = {"菜单表","序号"})
    private Long idx;
    /**
    * 描述
    */
    @ExcelProperty(value = {"菜单表","描述"})
    private String info;
    /**
    * 数据版本
    */
    @ExcelProperty(value = {"菜单表","数据版本"})
    private Long version;
    /**
    * 0未删除，1已删除
    */
    @ExcelProperty(value = {"菜单表","0未删除，1已删除"})
    private Integer deleted;
    /**
    * 创建时间
    */
    @ExcelProperty(value = {"菜单表","创建时间"})
    private Date created;
    /**
    * 修改时间
    */
    @ExcelProperty(value = {"菜单表","修改时间"})
    private Date updated;
}
