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
* 举报表
* @TableName report
*/
@HeadStyle(horizontalAlignment = HorizontalAlignmentEnum.LEFT, verticalAlignment = VerticalAlignmentEnum.CENTER)
@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.LEFT, verticalAlignment = VerticalAlignmentEnum.CENTER)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "report")
public class Report implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
    * 主键
    */
    @ExcelProperty(value = {"举报表","主键"})
    private Long id;
    /**
    * 集次ID，集次表外键
    */
    @ExcelProperty(value = {"举报表","集次ID，集次表外键"})
    private Long fkEpisodeId;
    /**
    * 举报人ID，用户表外键
    */
    @ExcelProperty(value = {"举报表","举报人ID，用户表外键"})
    private Long fkUserId;
    /**
    * 举报人昵称，冗余
    */
    @ExcelProperty(value = {"举报表","举报人昵称，冗余"})
    private String nickname;
    /**
    * 举报内容
    */
    @ExcelProperty(value = {"举报表","举报内容"})
    private String content;
    /**
    * 数据版本
    */
    @ExcelProperty(value = {"举报表","数据版本"})
    private Long version;
    /**
    * 0未删除，1已删除
    */
    @ExcelProperty(value = {"举报表","0未删除，1已删除"})
    private Integer deleted;
    /**
    * 创建时间
    */
    @ExcelProperty(value = {"举报表","创建时间"})
    private Date created;
    /**
    * 修改时间
    */
    @ExcelProperty(value = {"举报表","修改时间"})
    private Date updated;
}
