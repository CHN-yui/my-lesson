package com.mdkj.service;

import com.mdkj.domain.Menu;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


/**
* 针对表【menu(菜单表)】的数据库操作Service
*/
public interface MenuService extends IService<Menu>{
    /**
    * 查询全部
    */
    List<Menu> selectAll();

    /**
    * 条件查询
    */
    List<Menu> selectList(Menu iMenu);

    /**
    * 新增
    */
    void insert(Menu iMenu);

    /**
    * 删除数据
    */
    void delete(String ids);

    /**
    * 修改
    */
    void update(Menu iMenu);

    /**
    * 分页查询
    */
    IPage<Menu> pageList(Menu iMenu, Integer page, Integer size);

    /**
    * 导出Excel数据
    */
    List<Menu> getExcelData();
}
