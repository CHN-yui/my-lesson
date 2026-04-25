package com.mdkj.mapper;

import com.mdkj.domain.Coupons;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* 针对表【coupons(优惠卷表)】的数据库操作Mapper
* @utils com.mdkj.domain.Coupons
*/
@Mapper
public interface CouponsMapper extends BaseMapper<Coupons> {

}




