package com.mdkj.mapper;

import com.mdkj.domain.Cart;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* 针对表【cart(购物车表)】的数据库操作Mapper
* @utils com.mdkj.domain.Cart
*/
@Mapper
public interface CartMapper extends BaseMapper<Cart> {

}




