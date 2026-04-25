package com.mdkj.mapper;

import com.mdkj.domain.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* 针对表【order_detail(订单明细表)】的数据库操作Mapper
* @utils com.mdkj.domain.OrderDetail
*/
@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {

}




