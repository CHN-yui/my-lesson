package com.mdkj.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/** 分页传入类 */
@ToString(callSuper = true)
@Data
public class PageVO<T> implements Serializable {
    private Long pageNum;
    private Integer pageSize;
    private Long totalRow;
    private Integer totalPage;
    private List<T> records;
}