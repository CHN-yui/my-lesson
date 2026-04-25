package com.mdkj.mapper;

import com.mdkj.domain.Episode;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* 针对表【episode(集次表)】的数据库操作Mapper
* @utils com.mdkj.domain.Episode
*/
@Mapper
public interface EpisodeMapper extends BaseMapper<Episode> {

}




