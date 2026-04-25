package com.mdkj.service;

import com.mdkj.domain.Category;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


/**
* 针对表【category(课程类别表)】的数据库操作Service
*/
public interface CategoryService extends IService<Category>{
    /**
    * 查询全部
    */
    List<Category> selectAll();

    /**
    * 条件查询
    */
    List<Category> selectList(Category iCategory);

    /**
    * 新增
    */
    void insert(Category iCategory);

    /**
    * 删除数据
    */
    void delete(String ids);

    /**
    * 修改
    */
    void update(Category iCategory);

    /**
    * 分页查询
    */
    IPage<Category> pageList(Category iCategory, Integer page, Integer size);

    /**
    * 导出Excel数据
    */
    List<Category> getExcelData();
}
