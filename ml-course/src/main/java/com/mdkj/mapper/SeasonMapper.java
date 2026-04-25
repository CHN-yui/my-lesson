package com.mdkj.mapper;

import com.mdkj.domain.Season;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* 针对表【season(季次表)】的数据库操作Mapper
* @utils com.mdkj.domain.Season
*/
@Mapper
public interface SeasonMapper extends BaseMapper<Season> {

}




