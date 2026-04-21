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
* 用户角色关系表
* @TableName user_role
*/
@HeadStyle(horizontalAlignment = HorizontalAlignmentEnum.LEFT, verticalAlignment = VerticalAlignmentEnum.CENTER)
@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.LEFT, verticalAlignment = VerticalAlignmentEnum.CENTER)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "user_role")
public class UserRole implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
    * 主键
    */
    @ExcelProperty(value = {"用户角色关系表","主键"})
    private Long id;
    /**
    * 用户ID，用户表外键
    */
    @ExcelProperty(value = {"用户角色关系表","用户ID，用户表外键"})
    private Long fkUserId;
    /**
    * 角色ID，角色表外键
    */
    @ExcelProperty(value = {"用户角色关系表","角色ID，角色表外键"})
    private Long fkRoleId;
    /**
    * 数据版本
    */
    @ExcelProperty(value = {"用户角色关系表","数据版本"})
    private Long version;
    /**
    * 0未删除，1已删除
    */
    @ExcelProperty(value = {"用户角色关系表","0未删除，1已删除"})
    private Integer deleted;
    /**
    * 创建时间
    */
    @ExcelProperty(value = {"用户角色关系表","创建时间"})
    private Date created;
    /**
    * 修改时间
    */
    @ExcelProperty(value = {"用户角色关系表","修改时间"})
    private Date updated;
}
