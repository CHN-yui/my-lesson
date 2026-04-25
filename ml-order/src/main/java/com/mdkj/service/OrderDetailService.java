package com.mdkj.service;

import com.mdkj.domain.OrderDetail;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


/**
* 针对表【order_detail(订单明细表)】的数据库操作Service
*/
public interface OrderDetailService extends IService<OrderDetail>{
    /**
    * 查询全部
    */
    List<OrderDetail> selectAll();

    /**
    * 条件查询
    */
    List<OrderDetail> selectList(OrderDetail iOrderDetail);

    /**
    * 新增
    */
    void insert(OrderDetail iOrderDetail);

    /**
    * 删除数据
    */
    void delete(String ids);

    /**
    * 修改
    */
    void update(OrderDetail iOrderDetail);

    /**
    * 分页查询
    */
    IPage<OrderDetail> pageList(OrderDetail iOrderDetail, Integer page, Integer size);

    /**
    * 导出Excel数据
    */
    List<OrderDetail> getExcelData();
}
