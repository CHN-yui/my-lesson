package com.mdkj.service;

import com.mdkj.domain.Coupons;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


/**
* 针对表【coupons(优惠卷表)】的数据库操作Service
*/
public interface CouponsService extends IService<Coupons>{
    /**
    * 查询全部
    */
    List<Coupons> selectAll();

    /**
    * 条件查询
    */
    List<Coupons> selectList(Coupons iCoupons);

    /**
    * 新增
    */
    void insert(Coupons iCoupons);

    /**
    * 删除数据
    */
    void delete(String ids);

    /**
    * 修改
    */
    void update(Coupons iCoupons);

    /**
    * 分页查询
    */
    IPage<Coupons> pageList(Coupons iCoupons, Integer page, Integer size);

    /**
    * 导出Excel数据
    */
    List<Coupons> getExcelData();

    /**
     * 根据口令查询
     */
    Coupons queryByCode(String code);
}
