package com.mdkj.service;

import com.mdkj.domain.UserRole;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


/**
* 针对表【user_role(用户角色关系表)】的数据库操作Service
*/
public interface UserRoleService extends IService<UserRole>{
    /**
    * 查询全部
    */
    List<UserRole> selectAll();

    /**
    * 条件查询
    */
    List<UserRole> selectList(UserRole iUserRole);

    /**
    * 新增
    */
    void insert(UserRole iUserRole);

    /**
    * 删除数据
    */
    void delete(String ids);

    /**
    * 修改
    */
    void update(UserRole iUserRole);

    /**
    * 分页查询
    */
    IPage<UserRole> pageList(UserRole iUserRole, Integer page, Integer size);

    /**
    * 导出Excel数据
    */
    List<UserRole> getExcelData();

    /**
     * 查询用户的角色ID列表
     */
    List<Long> getRoleIdsByUserId(Long userId);

    /**
     * 修改用户角色
     */
    void updateUserRoles(Long userId, List<Long> roleIds);
}
