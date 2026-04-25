package com.mdkj.mapper;

import com.mdkj.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* 针对表【order(订单表)】的数据库操作Mapper
* @utils com.mdkj.domain.Order
*/
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}




