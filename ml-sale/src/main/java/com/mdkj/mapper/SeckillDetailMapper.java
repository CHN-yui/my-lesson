package com.mdkj.mapper;

import com.mdkj.domain.SeckillDetail;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* 针对表【seckill_detail(秒杀明细表)】的数据库操作Mapper
* @utils com.mdkj.domain.SeckillDetail
*/
@Mapper
public interface SeckillDetailMapper extends BaseMapper<SeckillDetail> {

}




