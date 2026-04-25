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
* 评论表
* @TableName comment
*/
@HeadStyle(horizontalAlignment = HorizontalAlignmentEnum.LEFT, verticalAlignment = VerticalAlignmentEnum.CENTER)
@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.LEFT, verticalAlignment = VerticalAlignmentEnum.CENTER)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "comment")
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
    * 主键
    */
    @ExcelProperty(value = {"评论表","主键"})
    private Long id;
    /**
    * 集次ID，集次表外键
    */
    @ExcelProperty(value = {"评论表","集次ID，集次表外键"})
    private Long fkEpisodeId;
    /**
    * 评论人ID，用户表外键
    */
    @ExcelProperty(value = {"评论表","评论人ID，用户表外键"})
    private Long fkUserId;
    /**
    * 评论人昵称，冗余
    */
    @ExcelProperty(value = {"评论表","评论人昵称，冗余"})
    private String nickname;
    /**
    * 评论人头像，冗余
    */
    @ExcelProperty(value = {"评论表","评论人头像，冗余"})
    private String avatar;
    /**
    * 评论人省份，冗余
    */
    @ExcelProperty(value = {"评论表","评论人省份，冗余"})
    private String province;
    /**
    * 父评论主键，0视为根节点
    */
    @ExcelProperty(value = {"评论表","父评论主键，0视为根节点"})
    private Long pid;
    /**
    * 评论内容
    */
    @ExcelProperty(value = {"评论表","评论内容"})
    private String content;
    /**
    * 数据版本
    */
    @ExcelProperty(value = {"评论表","数据版本"})
    private Long version;
    /**
    * 0未删除，1已删除
    */
    @ExcelProperty(value = {"评论表","0未删除，1已删除"})
    private Integer deleted;
    /**
    * 创建时间
    */
    @ExcelProperty(value = {"评论表","创建时间"})
    private Date created;
    /**
    * 修改时间
    */
    @ExcelProperty(value = {"评论表","修改时间"})
    private Date updated;
}
