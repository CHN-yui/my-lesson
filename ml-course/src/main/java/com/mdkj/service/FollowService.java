package com.mdkj.service;

import com.mdkj.domain.Follow;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


/**
* 针对表【follow(收藏表)】的数据库操作Service
*/
public interface FollowService extends IService<Follow>{
    /**
    * 查询全部
    */
    List<Follow> selectAll();

    /**
    * 条件查询
    */
    List<Follow> selectList(Follow iFollow);

    /**
    * 新增
    */
    void insert(Follow iFollow);

    /**
    * 删除数据
    */
    void delete(String ids);

    /**
    * 修改
    */
    void update(Follow iFollow);

    /**
    * 分页查询
    */
    IPage<Follow> pageList(Follow iFollow, Integer page, Integer size);

    /**
    * 导出Excel数据
    */
    List<Follow> getExcelData();
}
