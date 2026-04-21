package com.mdkj.service;

import com.mdkj.domain.Role;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


/**
* 针对表【role(角色表)】的数据库操作Service
*/
public interface RoleService extends IService<Role>{
    /**
    * 查询全部
    */
    List<Role> selectAll();

    /**
    * 条件查询
    */
    List<Role> selectList(Role iRole);

    /**
    * 新增
    */
    void insert(Role iRole);

    /**
    * 删除数据
    */
    void delete(String ids);

    /**
    * 修改
    */
    void update(Role iRole);

    /**
    * 分页查询
    */
    IPage<Role> pageList(Role iRole, Integer page, Integer size);

    /**
    * 导出Excel数据
    */
    List<Role> getExcelData();
}
