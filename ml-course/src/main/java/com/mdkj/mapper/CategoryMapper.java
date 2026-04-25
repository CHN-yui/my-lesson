package com.mdkj.mapper;

import com.mdkj.domain.Category;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* 针对表【category(课程类别表)】的数据库操作Mapper
* @utils com.mdkj.domain.Category
*/
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}




