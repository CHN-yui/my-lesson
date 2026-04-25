package com.mdkj.mapper;

import com.mdkj.domain.Seckill;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* 针对表【seckill(秒杀表)】的数据库操作Mapper
* @utils com.mdkj.domain.Seckill
*/
@Mapper
public interface SeckillMapper extends BaseMapper<Seckill> {

}




