package com.mdkj.service;

import com.mdkj.domain.Cart;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


/**
* 针对表【cart(购物车表)】的数据库操作Service
*/
public interface CartService extends IService<Cart>{
    /**
    * 查询全部
    */
    List<Cart> selectAll();

    /**
    * 条件查询
    */
    List<Cart> selectList(Cart iCart);

    /**
    * 新增
    */
    void insert(Cart iCart);

    /**
    * 删除数据
    */
    void delete(String ids);

    /**
    * 修改
    */
    void update(Cart iCart);

    /**
    * 分页查询
    */
    IPage<Cart> pageList(Cart iCart, Integer page, Integer size);

    /**
    * 导出Excel数据
    */
    List<Cart> getExcelData();

    /**
     * 清空购物车
     */
    void clearByUserId(Long userId);
}
