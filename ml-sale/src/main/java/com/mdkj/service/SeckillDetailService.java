package com.mdkj.service;

import com.mdkj.domain.SeckillDetail;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


/**
* 针对表【seckill_detail(秒杀明细表)】的数据库操作Service
*/
public interface SeckillDetailService extends IService<SeckillDetail>{
    /**
    * 查询全部
    */
    List<SeckillDetail> selectAll();

    /**
    * 条件查询
    */
    List<SeckillDetail> selectList(SeckillDetail iSeckillDetail);

    /**
    * 新增
    */
    void insert(SeckillDetail iSeckillDetail);

    /**
    * 删除数据
    */
    void delete(String ids);

    /**
    * 修改
    */
    void update(SeckillDetail iSeckillDetail);

    /**
    * 分页查询
    */
    IPage<SeckillDetail> pageList(SeckillDetail iSeckillDetail, Integer page, Integer size);

    /**
    * 导出Excel数据
    */
    List<SeckillDetail> getExcelData();
}
