package com.mdkj.service;

import com.mdkj.domain.Season;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


/**
* 针对表【season(季次表)】的数据库操作Service
*/
public interface SeasonService extends IService<Season>{
    /**
    * 查询全部
    */
    List<Season> selectAll();

    /**
    * 条件查询
    */
    List<Season> selectList(Season iSeason);

    /**
    * 新增
    */
    void insert(Season iSeason);

    /**
    * 删除数据
    */
    void delete(String ids);

    /**
    * 修改
    */
    void update(Season iSeason);

    /**
    * 分页查询
    */
    IPage<Season> pageList(Season iSeason, Integer page, Integer size);

    /**
    * 导出Excel数据
    */
    List<Season> getExcelData();
}
