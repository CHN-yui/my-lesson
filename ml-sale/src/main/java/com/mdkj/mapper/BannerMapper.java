package com.mdkj.mapper;

import com.mdkj.domain.Banner;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* 针对表【banner(横幅表)】的数据库操作Mapper
* @utils com.mdkj.domain.Banner
*/
@Mapper
public interface BannerMapper extends BaseMapper<Banner> {

}




