package com.mdkj.service;

import com.mdkj.domain.Order;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


/**
* 针对表【order(订单表)】的数据库操作Service
*/
public interface OrderService extends IService<Order>{
    /**
    * 查询全部
    */
    List<Order> selectAll();

    /**
    * 条件查询
    */
    List<Order> selectList(Order iOrder);

    /**
    * 新增
    */
    void insert(Order iOrder);

    /**
    * 删除数据
    */
    void delete(String ids);

    /**
    * 修改
    */
    void update(Order iOrder);

    /**
    * 分页查询
    */
    IPage<Order> pageList(Order iOrder, Integer page, Integer size);

    /**
    * 导出Excel数据
    */
    List<Order> getExcelData();
}
