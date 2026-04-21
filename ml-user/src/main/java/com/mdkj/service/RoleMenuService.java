package com.mdkj.service;

import com.mdkj.domain.RoleMenu;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


/**
* 针对表【role_menu(角色菜单关系表)】的数据库操作Service
*/
public interface RoleMenuService extends IService<RoleMenu>{
    /**
    * 查询全部
    */
    List<RoleMenu> selectAll();

    /**
    * 条件查询
    */
    List<RoleMenu> selectList(RoleMenu iRoleMenu);

    /**
    * 新增
    */
    void insert(RoleMenu iRoleMenu);

    /**
    * 删除数据
    */
    void delete(String ids);

    /**
    * 修改
    */
    void update(RoleMenu iRoleMenu);

    /**
    * 分页查询
    */
    IPage<RoleMenu> pageList(RoleMenu iRoleMenu, Integer page, Integer size);

    /**
    * 导出Excel数据
    */
    List<RoleMenu> getExcelData();
}
