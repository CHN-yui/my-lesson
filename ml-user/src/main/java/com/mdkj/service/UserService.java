package com.mdkj.service;

import com.mdkj.domain.User;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


/**
* 针对表【user(用户表)】的数据库操作Service
*/
public interface UserService extends IService<User>{
    /**
    * 查询全部
    */
    List<User> selectAll();

    /**
    * 条件查询
    */
    List<User> selectList(User iUser);

    /**
    * 新增
    */
    void insert(User iUser);

    /**
    * 删除数据
    */
    void delete(String ids);

    /**
    * 修改
    */
    void update(User iUser);

    /**
    * 分页查询
    */
    IPage<User> pageList(User iUser, Integer page, Integer size);

    /**
    * 导出Excel数据
    */
    List<User> getExcelData();
}
