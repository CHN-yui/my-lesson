package com.mdkj.mapper;

import com.mdkj.domain.Follow;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* 针对表【follow(收藏表)】的数据库操作Mapper
* @utils com.mdkj.domain.Follow
*/
@Mapper
public interface FollowMapper extends BaseMapper<Follow> {

}




