package com.mdkj.service;

import com.mdkj.domain.Seckill;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


/**
* 针对表【seckill(秒杀表)】的数据库操作Service
*/
public interface SeckillService extends IService<Seckill>{
    /**
    * 查询全部
    */
    List<Seckill> selectAll();

    /**
    * 条件查询
    */
    List<Seckill> selectList(Seckill iSeckill);

    /**
    * 新增
    */
    void insert(Seckill iSeckill);

    /**
    * 删除数据
    */
    void delete(String ids);

    /**
    * 修改
    */
    void update(Seckill iSeckill);

    /**
    * 分页查询
    */
    IPage<Seckill> pageList(Seckill iSeckill, Integer page, Integer size);

    /**
    * 导出Excel数据
    */
    List<Seckill> getExcelData();

    /**
     * 查看今日秒杀
     */
    List<Seckill> todayList();

    /**
     * 秒杀活动预热
     */
    Map<String, Object> preheat(Long seckillId);

    /**
     * 开始秒杀课程
     */
    void startSeckill(Long seckillId);
}
