package com.mdkj.mapper;

import com.mdkj.domain.Course;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* 针对表【course(课程表)】的数据库操作Mapper
* @utils com.mdkj.domain.Course
*/
@Mapper
public interface CourseMapper extends BaseMapper<Course> {

}




