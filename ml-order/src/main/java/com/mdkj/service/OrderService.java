package com.mdkj.service;

import com.mdkj.domain.Order;
import com.mdkj.dto.CreateOrderDTO;
import java.util.List;
import java.util.Map;
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

    /**
     * 订单统计
     */
    Map<String, Object> statistics();

    /**
     * 创建预支付订单
     */
    Map<String, Object> createPrepayOrder(CreateOrderDTO dto);

    /**
     * 获取支付二维码
     */
    Map<String, Object> getPayQrcode(Long orderId);

    /**
     * 查询订单状态
     */
    Map<String, Object> queryOrderStatus(Long orderId);

    /**
     * 处理支付回调
     */
    void handlePayCallback(Long orderId, String tradeNo, Integer payType, String callbackMsg);

    /**
     * 支付宝回调处理
     */
    void handleAliPayCallback(Map<String, String> params);
}
