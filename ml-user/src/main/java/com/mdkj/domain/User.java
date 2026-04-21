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
* 用户表
* @TableName user
*/
@HeadStyle(horizontalAlignment = HorizontalAlignmentEnum.LEFT, verticalAlignment = VerticalAlignmentEnum.CENTER)
@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.LEFT, verticalAlignment = VerticalAlignmentEnum.CENTER)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
    * 主键
    */
    @ExcelProperty(value = {"用户表","主键"})
    private Long id;
    /**
    * 账号
    */
    @ExcelProperty(value = {"用户表","账号"})
    private String username;
    /**
    * 密码
    */
    @ExcelProperty(value = {"用户表","密码"})
    private String password;
    /**
    * 昵称
    */
    @ExcelProperty(value = {"用户表","昵称"})
    private String nickname;
    /**
    * 邮箱
    */
    @ExcelProperty(value = {"用户表","邮箱"})
    private String email;
    /**
    * 省份
    */
    @ExcelProperty(value = {"用户表","省份"})
    private String province;
    /**
    * 姓名
    */
    @ExcelProperty(value = {"用户表","姓名"})
    private String realname;
    /**
    * 头像
    */
    @ExcelProperty(value = {"用户表","头像"})
    private String avatar;
    /**
    * 星座
    */
    @ExcelProperty(value = {"用户表","星座"})
    private String zodiac;
    /**
    * 手机
    */
    @ExcelProperty(value = {"用户表","手机"})
    private String phone;
    /**
    * 身份证号
    */
    @ExcelProperty(value = {"用户表","身份证号"})
    private String idcard;
    /**
    * 性别，0女，1男，2保密
    */
    @ExcelProperty(value = {"用户表","性别，0女，1男，2保密"})
    private Integer gender;
    /**
    * 年龄
    */
    @ExcelProperty(value = {"用户表","年龄"})
    private Integer age;
    /**
    * 描述
    */
    @ExcelProperty(value = {"用户表","描述"})
    private String info;
    /**
    * 数据版本
    */
    @ExcelProperty(value = {"用户表","数据版本"})
    private Long version;
    /**
    * 0未删除，1已删除
    */
    @ExcelProperty(value = {"用户表","0未删除，1已删除"})
    private Integer deleted;
    /**
    * 创建时间
    */
    @ExcelProperty(value = {"用户表","创建时间"})
    private Date created;
    /**
    * 修改时间
    */
    @ExcelProperty(value = {"用户表","修改时间"})
    private Date updated;
}
